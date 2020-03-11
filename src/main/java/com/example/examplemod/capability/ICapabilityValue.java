package com.example.examplemod.capability;

public interface ICapabilityValue {
    int getValue();

    void setValue(int value);

    class DefaultImplement implements ICapabilityValue {

        private int value = 0;

        @Override
        public int getValue() {
            return value;
        }

        @Override
        public void setValue(int value) {
            this.value = value;
        }
    }
}
