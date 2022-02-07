package ABSTRACTION.TrafficLights;

public class TrafficLight {
    private Lights light;

    public TrafficLight(Lights light) {
        this.light = light;
    }

    public void setLight(Lights light) {
        this.light = light;
    }

    public void changeColor(){
        switch (this.light){
            case RED:
                this.light = Lights.GREEN;
                break;
            case GREEN:
                this.light = Lights.YELLOW;
                break;
            case YELLOW:
                this.light = Lights.RED;
                break;
        }
    }

    public Lights getLight() {
        return light;
    }
}
