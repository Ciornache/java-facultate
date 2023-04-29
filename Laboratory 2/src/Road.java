public class Road {

    private int length, speedLimit;
    private _road type;

    public Road(int length, int speedLimit, _road type){

        setLength(length);
        setSpeedLimit(speedLimit);
        setType(type);
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getSpeedLimit() {
        return speedLimit;
    }

    public void setSpeedLimit(int speedLimit) {
        this.speedLimit = speedLimit;
    }

    public _road getType() {
        return type;
    }

    public void setType(_road type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "road{" +
                "length=" + length +
                ", speedLimit=" + speedLimit +
                ", type=" + type +
                '}';
    }

    @Override
    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;
        if(obj.getClass() != this.getClass())
            return false;
        Road road = (Road)(obj);
        return road.length == this.length && road.speedLimit == this.speedLimit && road.type == this.type;
    }
}
