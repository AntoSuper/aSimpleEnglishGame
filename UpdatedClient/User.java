public class User {
    private String nickname;
    private String character;
    private boolean inGame = false;
    private boolean dead = false;
    private boolean question = false;
    private int lives=3;
    private int score=0;
    private int x = 7 * Game.BLOCK_SIZE;
    private int y = 11 * Game.BLOCK_SIZE;
    private int dx = 0;
    private int dy = 0;
    private int req_dx = 0;
    private int req_dy = 0;

    public User (String nickname) {
        this.nickname = nickname;
    }

    public User (String nickname, String character) {
        this.nickname = nickname;
        this.character = character;
    }

    public String getNickname () {
        return nickname;
    }

    public String getCharacter () {
        return character;
    }

    public boolean getInGame () {
        return inGame;
    }

    public boolean getDead () {
        return dead;
    }

    public boolean getQuestion () {
        return question;
    }

    public int getLives () {
        return lives;
    }

    public int getScore () {
        return score;
    }

    public int getX () {
        return x;
    }

    public int getY () {
        return y;
    }

    public int getDX () {
        return dx;
    }

    public int getDY () {
        return dy;
    }

    public int getReqDX () {
        return req_dx;
    }

    public int getReqDY () {
        return req_dy;
    }

    public void setNickname (String nickname) {
        this.nickname=nickname;
    }

    public void setCharacter (String character) {
        this.character = character;
    }

    public void setInGame (boolean inGame) {
        this.inGame=inGame;
    }

    public void setDead (boolean dead) {
        this.dead=dead;
    }

    public void setQuestion (boolean question) {
        this.question=question;
    }

    public void setLives (int lives) {
        this.lives=lives;
    }

    public void setScore (int score) {
        this.score=score;
    }

    public void setX (int x) {
        this.x=x;
    }

    public void setY (int y) {
        this.y=y;
    }

    public void setDX (int dx) {
        this.dx=dx;
    }

    public void setDY (int dy) {
        this.dy=dy;
    }

    public void setReqDX (int req_dx) {
        this.req_dx=req_dx;
    }

    public void setReqDY (int req_dy) {
        this.req_dy=req_dy;
    }

    public String toString () {
        return this.nickname+"§"+this.character+"§"+this.inGame+"§"+this.dead+"§"+this.lives+"§"+this.score+"§"+this.x+"§"+this.y+"§"+this.dx+"§"+this.dy+"§"+this.req_dx+"§"+this.req_dy;
    }

    public boolean equals (User x) {
        if (this.nickname.equals(x.getNickname())) {
            return true;
        }
        return false;
    }


}
