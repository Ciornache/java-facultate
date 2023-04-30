public class Car {

    private final int speed, wheels;

    private final int engine;

    private final String brand;


    private Car(Builder builder)
    {
        this.brand = builder.getBrand();
        this.engine = builder.getEngine();
        this.speed = builder.getSpeed();
        this.wheels = builder.getWheels();
    }

    public int getSpeed(){
        return speed;
    }

    public static class Builder
    {
        private int speed = 0, wheels = 0;

        private int engine = 0;

        private String brand = null;

        Builder buildEngine(int engine)
        {
            setEngine(engine);
            return this;
        }
        Builder buildBrand(String brand)
        {
            setBrand(brand);
            return this;
        }
        Builder buildWheels(int wheels)
        {
            setWheels(wheels);
            return this;
        }
        Builder buildSpeed(int speed)
        {
            setSpeed(speed);
            return this;
        }

        public int getSpeed() {
            return speed;
        }

        public void setSpeed(int speed) {
            this.speed = speed;
        }

        public int getWheels() {
            return wheels;
        }

        public void setWheels(int wheels) {
            this.wheels = wheels;
        }

        public int getEngine() {
            return engine;
        }

        public void setEngine(int engine) {
            this.engine = engine;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public Car buildCar()  {
            return new Car(this);
        }

    }

}
