package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Model {
    private static final int WIDHT = 600;
    private static final int HEIGHT = 600;
    private static final double GAS_CONSTANT = 8.314;
    private static final double AVOGADRO_CONSTANT = 6.022 * Math.pow(10, 23);

    private List<Particle> particles = new ArrayList<>();

    private float particleRadius = 7.0F;
    private float particleSpeed = 6.0F;
    private float valuePressure;
    private float valueTemperature;
    private float valueVolume;
    private float valueAmountSubstance;

    public void updateParticleCount(int count) {
        if (count < particles.size()) {
            particles.subList(count, particles.size()).clear();
        } else {
            Random random = new Random();
            for (int i = particles.size(); i < count; i++) {
                double x = random.nextDouble() * (WIDHT - 2 * particleRadius) + particleRadius;
                double y = random.nextDouble() * (HEIGHT - 2 * particleRadius) + particleRadius;
                particles.add(new Particle(x, y));
            }
        }
    }

    public void updateParticles() {
        for (Particle particle : particles) {
            particle.x += (Math.random() - 0.5) * particleSpeed / 4;
            particle.y += (Math.random() - 0.5) * particleSpeed / 4;
            particle.x = Math.min(Math.max(particle.x, particleRadius), WIDHT - particleRadius);
            particle.y = Math.min(Math.max(particle.y, particleRadius), HEIGHT - particleRadius);
        }
    }

    public float calculatePressure(float volume, float temperature, float substance) {
        return (float) (temperature * GAS_CONSTANT * (substance/AVOGADRO_CONSTANT)) / volume;
    }

    public List<Particle> getParticles() {
        return particles;
    }

    public float getParticleRadius() {
        return particleRadius;
    }

    public void setParticleRadius(float particleRadius) {
        this.particleRadius = particleRadius;
    }

    public float getParticleSpeed() {
        return particleSpeed;
    }

    public void setParticleSpeed(float particleSpeed) {
        this.particleSpeed = particleSpeed;
    }

    public float getValuePressure() {
        return valuePressure;
    }

    public void setValuePressure(float valuePressure) {
        this.valuePressure = valuePressure;
    }

    public float getValueTemperature() {
        return valueTemperature;
    }

    public void setValueTemperature(float valueTemperature) {
        this.valueTemperature = valueTemperature;
    }

    public float getValueVolume() {
        return valueVolume;
    }

    public void setValueVolume(float valueVolume) {
        this.valueVolume = valueVolume;
    }

    public float getValueAmountSubstance() {
        return valueAmountSubstance;
    }

    public void setValueAmountSubstance(float valueAmountSubstance) {
        this.valueAmountSubstance = valueAmountSubstance;
    }
}
