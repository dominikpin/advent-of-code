package year2015.day14;

public class Reindeer {

    private int speed;
    private int time;
    private int rest;
    private int timeLeft;
    private int distance;
    private boolean isMoving;

    public Reindeer(int speed, int time, int rest) {
        this.speed = speed;
        this.time = time;
        this.rest = rest;
        this.timeLeft = time;
        this.distance = 0;
        this.isMoving = true;
    }

    public int advanceOneSecond() {
        if (this.isMoving) {
            this.distance += this.speed;
        }
        this.timeLeft--;
        if (this.timeLeft == 0) {
            if (this.isMoving) {
                this.timeLeft = this.rest;
            } else {
                this.timeLeft = this.time;
            }
            this.isMoving = !this.isMoving;
        }
        return this.distance;
    }
}
