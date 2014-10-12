package se.fredin.gdxtensions.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool.PooledEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;

/**
 * Helper class for all the particles in the game.
 * Will not do anything if we are on a low end device.
 * Makes for cleaner code.
 * @author Johan Fredin
 *
 */
public class ParticleHelper implements Disposable {
	
	private ParticleEffect particleEffect;
	private ParticleEffectPool particleEffectPool;
	private Array<PooledEffect> pooledEffectsArray;
	private float speed = 1f;
	private boolean isParticlesEnabled;
	private boolean isPaused;
	
	/**
	 * Create a new instance with a given particle name and capacity for our pool
	 * If we are running on a low end device we will not instantiate our instances.
	 * @param particleName the name of the particle effect. the helper will search for the 
	 * particle in the "effects" location of the assets folder.
	 * @param initialCapacity the initial capacity of the pool
	 * @param maxCapacity the max capacity of the pool
	 */
	public ParticleHelper(String particleName, int initialCapacity, int maxCapacity) {
		this.particleEffect = new ParticleEffect();
		this.particleEffect.load(Gdx.files.internal("effects/" + particleName), Gdx.files.internal("effects"));
		this.particleEffectPool = new ParticleEffectPool(particleEffect, initialCapacity, maxCapacity);
		this.pooledEffectsArray = new Array<PooledEffect>();
	}
	
	/**
	 * Create a new instance with a given particle name and capacity for our pool
	 * We are also able to set the update speed of the particle here.
	 * @param particleName the name of the particle effect. the helper will search for the 
	 * particle in the "effects" location of the assets folder.
	 * @param initialCapacity the initial capacity of the pool
	 * @param maxCapacity the max capacity of the pool
	 * @param speed the update speed of this particle effect
	 */
	public ParticleHelper(String particleName, int initialCapacity, int maxCapacity, float speed) {
		this(particleName, initialCapacity, maxCapacity);
		this.speed = speed;
	}
	
	/**
	 * Set the speed of our particles
	 * @param speed
	 */
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	
	/**
	 * Renders the active particles
	 * @param batch 
	 */
	public void render(SpriteBatch batch) {
		if(enabled()) {
			for(PooledEffect pooledEffect : pooledEffectsArray) {
				pooledEffect.draw(batch);
			}
		}
	}
	
	/**
	 * Renders and updates the active particles, classes using this method
	 * will not need to call the tick method. if game is paused it will not update the particle
	 * @param batch
	 * @param deltaTime
	 */
	public void render(SpriteBatch batch, float deltaTime) {
		if(enabled()) {
			for(PooledEffect pooledEffect : pooledEffectsArray) {
				pooledEffect.draw(batch, isPaused ? 0.0f : deltaTime * speed);
			}
		}
	}
	
	/**
	 * Updates the active particles, when particle effect is complete we remove it from the list
	 * and put it back into the pool.
	 * @param deltaTime 
	 */
	public void tick(float deltaTime) {
		for(PooledEffect pooledEffect : pooledEffectsArray) {
			if(pooledEffect.isComplete()) {
				pooledEffectsArray.removeValue(pooledEffect, true);
				particleEffectPool.free(pooledEffect);
			} else {
				pooledEffect.update(deltaTime * speed);
			}
		}
	}
	
	public boolean isComplete() {
		if(enabled()) {
			for(PooledEffect pooledEffect : pooledEffectsArray) {
				if(pooledEffect.isComplete()) {
					return true;
				} 
			}
			return false;
		} 
		return true;
	}
	
	/**
	 * Add a particle at given position
	 * @param x the x position of the particle
	 * @param y the y position of the particle
	 * @param startRightAway if true then the effect will call its start method right away.
	 * this is usually the right approach but can sometimes cause weird effects.
	 */
	public void start(float x, float y, boolean startRightAway) {
		if(enabled()) {
			PooledEffect effect = particleEffectPool.obtain();
			effect.setPosition(x, y);
			pooledEffectsArray.add(effect);
			if(startRightAway) {
				effect.start();
			}
		}
	}
	
	@Override
	public void dispose() {
		particleEffectPool.clear();
		for(PooledEffect effect : pooledEffectsArray) {
			effect.dispose();
		}
		particleEffect.dispose();
	}
	
	/** 
	 * Sometimes effects may be null (for example with one way doors
	 * so we make this boolean here to prevent a bunch of if statements
	 * in the classes that use this class. Also, if game is run on a low end device
	 * this will almost always be null.
	 * @return whether or not our instances were instantiated.
	 */
	private boolean enabled() {
		return isParticlesEnabled;
	}

}
