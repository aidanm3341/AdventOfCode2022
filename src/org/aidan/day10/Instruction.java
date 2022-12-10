package org.aidan.day10;

public interface Instruction {
    void tick(State state);

    class AddX implements Instruction {
        private final int amount;

        public AddX(int amount) {
            this.amount = amount;
        }

        @Override
        public void tick(State state) {
            state.setxRegister(state.getxRegister() + amount);
            state.incrementCycleCount();
        }
    }

    class Noop implements Instruction {
        @Override
        public void tick(State state) {
            state.incrementCycleCount();
        }
    }
}
