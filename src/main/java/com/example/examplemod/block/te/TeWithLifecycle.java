package com.example.examplemod.block.te;

import com.elementtimes.elementcore.api.template.tileentity.interfaces.IMachineLifecycle;
import com.elementtimes.elementcore.api.template.tileentity.interfaces.IMachineTickable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

import java.util.HashSet;
import java.util.Set;

public class TeWithLifecycle extends TileEntity implements IMachineTickable, ITickable {

    private Set<IMachineLifecycle> mLifecycles = new HashSet<>();
    private boolean isWorking = false;
    private boolean isPause = false;
    private int processed = 0, unprocessed = 0;

    public TeWithLifecycle() {
        addLifeCycle(new IMachineLifecycle() {

            private int runTick = 0;
            private int waitTick = 0;

            @Override
            public void onTickStart() { }

            @Override
            public boolean onCheckStart() {
                waitTick++;
                return waitTick == 100;
            }

            @Override
            public void onStart() {
                System.out.println("onStart");
                waitTick = 0;
                setWorking(true);
            }

            @Override
            public boolean onLoop() {
                return runTick++ != 20;
            }

            @Override
            public boolean onFinish() {
                runTick = 0;
                setWorking(false);
                return true;
            }

            @Override
            public boolean onCheckFinish() {
                return runTick == 100;
            }

            @Override
            public void onPause() {
                if (waitTick == 0) {
                    System.out.println("onPause");
                }
                setWorking(false);
                waitTick++;
            }

            @Override
            public boolean onCheckResume() {
                return waitTick == 100;
            }

            @Override
            public void onResume() {
                System.out.println("onResume");
                waitTick = 0;
                setWorking(true);
            }

            @Override
            public void onTickFinish() { }
        });
    }

    @Override
    public Set<IMachineLifecycle> getAllLifecycles() {
        return mLifecycles;
    }

    @Override
    public void update() {
        IMachineTickable.super.update(this);
    }

    @Override
    public void interrupt() { }

    @Override
    public boolean isWorking() {
        return isWorking;
    }

    @Override
    public void setWorking(boolean isWorking) {
        this.isWorking = isWorking;
    }

    @Override
    public boolean isPause() {
        return isPause;
    }

    @Override
    public void setPause(boolean isPause) {
        this.isPause = isPause;
    }

    @Override
    public int getEnergyProcessed() {
        return processed;
    }

    @Override
    public void setEnergyProcessed(int processed) {
        this.processed = processed;
    }

    @Override
    public int getEnergyUnprocessed() {
        return unprocessed;
    }

    @Override
    public void setEnergyUnprocessed(int unprocessed) {
        this.unprocessed = unprocessed;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        IMachineTickable.super.deserializeNBT(compound);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        IMachineTickable.super.writeToNBT(compound);
        return super.writeToNBT(compound);
    }
}
