public class QuizCard {
    String question;
    String answser;

    public QuizCard(String q,String a){
        this.answser=a;
        this.question=q;
    }

    public String getAnswser() {
        return answser;
    }

    public String getQuestion() {
        return question;
    }
}
