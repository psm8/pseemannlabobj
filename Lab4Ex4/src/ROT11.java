public class ROT11 implements Algorithm {
    final String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    final int shift = 11;
    public String crypt(String s){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if       (c >= 'a' && c <= 'o') {c += shift;}
            else if  (c >= 'A' && c <= 'O') {c += shift;}
            else if  (c >= 'p' && c <= 'z') {c -= (alphabet.length()/2)-shift;}
            else if  (c >= 'P' && c <= 'Z') {c -= (alphabet.length()/2)-shift;}
            sb.append(c);
        }
        return sb.toString();
    }
    public String decrypt(String s){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if       (c >= 'a' && c <= 'k') {c += (alphabet.length()/2)-shift;}
            else if  (c >= 'A' && c <= 'K') {c += (alphabet.length()/2)-shift;}
            else if  (c >= 'l' && c <= 'z') {c -= shift;}
            else if  (c >= 'L' && c <= 'Z') {c -= shift;}
            sb.append(c);
        }
        return sb.toString();
    }
}
