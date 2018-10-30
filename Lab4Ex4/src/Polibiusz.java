public class Polibiusz implements Algorithm {
    public String crypt(String s){
        StringBuilder sb = new StringBuilder();
        int row, col;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if((c>='a' && c<='z')||(c>='A' && c<='Z')) {
                if (c >= 'a') {
                    if (c <= 'j') {
                        row = (int) Math.ceil((c - 'a') / 5) +1 ;
                        if (c == 'j') {
                            col = 4;
                        } else {
                            col = ((c - 'a') % 5) + 1;
                        }
                    } else {
                        row = (int) Math.ceil((c - 'a' - 1) / 5) +1;
                        col = ((c - 'a' - 1) % 5) + 1;
                    }
                } else {
                    if (c <= 'J') {
                        row = (int) Math.ceil((c - 'A') / 5) + 1;
                        if (c == 'J') {
                            col = 4;
                        } else {
                            col = ((c - 'A') % 5) + 1;
                        }
                    } else {
                        row = (int) Math.ceil((c - 'A' - 1) / 5) + 1;
                        col = ((c - 'A' - 1) % 5) + 1;
                    }
                }
                sb.append(row);
                sb.append(col);
            }
            else{sb.append(c);}
        }
        return sb.toString();
    }
    public String decrypt(String s){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i)>=49 && s.charAt(i)<=53){
                int row = Character.getNumericValue(s.charAt(i));
                int col = Character.getNumericValue(s.charAt(i+1));
                int sum = (5*(row-1)+col-1);
                if(sum<=8) {
                    char c = (char) ('a' + sum);
                    sb.append(c);
                }
                else{
                    char c = (char) ('a' + sum + 1);
                    sb.append(c);
                }
                i++;
            }
            else{
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
}
