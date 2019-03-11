public class Student {
    private String id;
    private String name;
    private int score_1, score_2, score_3, score_4, score_5;
    private int sum;
    private int rank;

    public Student(String id, String name, int score_1, int score_2, int score_3, int score_4, int score_5) {
        this.id = id;
        this.name = name;
        this.score_1 = score_1;
        this.score_2 = score_2;
        this.score_3 = score_3;
        this.score_4 = score_4;
        this.score_5 = score_5;
        Sum();
    }

    public int getSum() {
        return sum;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getScore_1() {
        return score_1;
    }

    public int getScore_2() {
        return score_2;
    }

    public int getScore_3() {
        return score_3;
    }

    public int getScore_4() {
        return score_4;
    }

    public int getScore_5() {
        return score_5;
    }

    public int getRank() {
        return rank;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void Sum() {
        this.sum = score_1 + score_2 + score_3 + score_4 + score_5;
    }
}
