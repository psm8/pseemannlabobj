import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(6666);
        } catch (IOException e) {
            System.out.println("Could not listen on port: 6666");
            System.exit(-1);
        }

        Socket clientSocket = null;
        try {
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.out.println("Accept failed: 6666");
            System.exit(-1);
        }
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        clientSocket.getInputStream()));
        String command;
        Map<String, SocketAddress> currentlyLogged =  new HashMap<>();

        while ((command = in.readLine()) != null) {
            SocketAddress clientAddresss = clientSocket.getRemoteSocketAddress();
            if (command.matches("LOGIN.*")) {
                login(out, command, currentlyLogged, clientAddresss);
            }
            else if (checkIfIdMatches(command, currentlyLogged, clientAddresss)) {
                if (command.matches("LOGOUT.*")) {
                    logout(out, command, currentlyLogged);
                    /*pliki dodane dla test;QsST*/
                } else if (command.matches("LS.*")) {
                    ls(out, command);

                } else if (command.matches("GET.*")) {
                    get(out, command);
                }
            }else {
                out.println("Wrong command");
            }
        }
        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();

    }

    private static boolean checkIfIdMatches(String command, Map<String, SocketAddress> currentlyLogged, SocketAddress clientAddresss){
        StringBuilder idSB = new StringBuilder();
        int idStarts = command.indexOf(" ")+1;
        try {
            for (int i = idStarts; i < idStarts+10; i++) {
                idSB.append(command.charAt(i));
            }
            String id = idSB.toString();
            if (currentlyLogged.get(id).equals(clientAddresss)) {
                return true;
            } else {
                return false;
            }
        }catch(Exception e){
            return false;
        }
    }

    private static void login(PrintWriter out, String command, Map<String, SocketAddress> currentlyLogged, SocketAddress clientAddresss){
        StringBuilder logAndPassSB = new StringBuilder();
        for (int i = 6; i < command.length(); i++) {
            logAndPassSB.append(command.charAt(i));
        }
        String logAndPass = logAndPassSB.toString();
        String[] logAndPassSplit = splitFixedSize(logAndPass,2,";");
        String login = logAndPassSplit[0];
        String password = logAndPassSplit[1];
        String[][] data = readFromFile("data/polish-dic.txt", "/");
        int levenshteinDistance = -1;
        boolean loginMatchesFlag = false;
        for (int i = 0; i < data.length; i++) {
            if(login.equals(data[i][0])){
                loginMatchesFlag = true;
                levenshteinDistance = LevenshteinDistance(password, data[i][1]);
            }
        }
        if(loginMatchesFlag){
            if(levenshteinDistance == 0){
                String id;
                try {
                    id = createID("data/ids.txt", login);
                    currentlyLogged.put(id, clientAddresss);
                }catch(IOException e){
                    out.println("Couldn't get ID");
                    return;
                }
                out.println("Your ID is "+id);
            }
            else{
                out.println("Password wrong by "+levenshteinDistance);
            }
        }
        else{
            out.println("Wrong login");
        }
    }

    private static void logout(PrintWriter out, String command, Map<String, SocketAddress> currentlyLogged){
        StringBuilder idSB = new StringBuilder();
        for (int i = 7; i < command.length(); i++) {
            idSB.append(command.charAt(i));
        }
        String id = idSB.toString();
        if(currentlyLogged.remove(id,currentlyLogged.get(id))){
            out.println("Successful logout");
        }else{
            out.println("Couldn't logout");
        }
    }

    private static void ls(PrintWriter out, String command){
        StringBuilder idSB = new StringBuilder();
        for (int i = 3; i < command.length(); i++) {
            idSB.append(command.charAt(i));
        }
        String id = idSB.toString();
        String[][] data = readFromFile("data/files.txt", "/");
        StringBuilder filesSB = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            if(id.equals(data[i][0])) {
                filesSB.append(data[i][1] + ";");
            }
        }
        if(!(filesSB.toString().equals(""))) {
            out.println(filesSB.toString());
        }else{
            out.println("No files available");
        }
    }

    private static void get(PrintWriter out, String command){
        StringBuilder idSB = new StringBuilder();
        for (int i = 4; i < 14; i++) {
            idSB.append(command.charAt(i));
        }
        String id = idSB.toString();
        StringBuilder fileNameSB = new StringBuilder();
        for (int i = 15; i < command.length(); i++) {
            fileNameSB.append(command.charAt(i));
        }
        String fileName = fileNameSB.toString();
        String[][] data = readFromFile("data/files.txt", "/");
        boolean fileExistsFlag = false;
        for (int i = 0; i < data.length; i++) {
            if(id.equals(data[i][0]) && fileName.equals(data[i][1])) {
                fileExistsFlag = true;
                break;
            }
        }
        if(fileExistsFlag) {
            try {
                FileReader in = new FileReader("data/"+fileName);
                BufferedReader br = new BufferedReader(in);
                String line = br.readLine();
                out.println(line);
            }catch(IOException e){
                out.println("Couldn't read file");
            }
        }
        else{
            out.println("No such file");
        }
    }

    private static String[][] readFromFile(String fileURI, String delimeter){
        List<String> lines;
        String[][] sArray = new String[0][];
        try {
            lines = Files.readAllLines(Paths.get(fileURI), StandardCharsets.UTF_8);
            sArray = new String[lines.size()][];
            for (int i = 0; i < lines.size(); i++) {
                sArray[i] = splitFixedSize(lines.get(i),2,delimeter);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sArray;
    }

    private static int LevenshteinDistance(String typedPassword, String orginalPassword){
        int[][] distance = new int[typedPassword.length()+1][orginalPassword.length()+1];
        for (int i = 0; i < distance.length; i++) {
            distance[i][0] = i;
        }

        for (int i = 0; i < distance[0].length; i++) {
            distance[0][i] = i;
        }

        for (int i = 1; i < distance.length; i++) {
            for (int j = 1; j < distance[0].length; j++) {
                int cost = 0;
                if(typedPassword.charAt(i-1)!=orginalPassword.charAt(j-1)) {
                    cost = 1;
                }
                distance[i][j] = Math.min(Math.min(distance[i-1][j]+1,distance[i][j-1]+1),distance[i-1][j-1]+cost);
            }
        }
        return distance[distance.length-1][distance[0].length-1];
    }

    private static String[] splitFixedSize(String line, int arraySize, String delimeter){
        String[] splittedSameSize = new String[arraySize];
        String[] splitted = line.split(delimeter);
        for (int i = 0; i < arraySize; i++) {
            if (splitted.length > i) {
                splittedSameSize[i] = splitted[i];
            } else {
                splittedSameSize[i] = "";
            }
        }
        return splittedSameSize;
    }

    private static String createID(String fileName, String login) throws IOException{
        PrintWriter saveId = new PrintWriter(new FileOutputStream(fileName,true ));
        FileReader in = new FileReader(fileName);
        BufferedReader br = new BufferedReader(in);
        int linesNumber = 0;
        String line;
        while ((line = br.readLine()) != null) {
            if(line.split("/")[0].equals(login)){
                in.close();
                return line.split("/")[1];
            }
            linesNumber++;
        }
        in.close();
        String idEnding = String.valueOf(linesNumber);
        StringBuilder id = new StringBuilder();
        for (int i = 0; i < 10 - idEnding.length(); i++) {
            id.append("0");
        }
        id.append(idEnding);
        saveId.println(login+"/"+id);
        saveId.close();
        return id.toString();

    }
}



