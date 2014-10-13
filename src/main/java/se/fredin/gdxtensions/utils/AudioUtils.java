package se.fredin.gdxtensions.utils;

import java.util.HashMap;
import java.util.Map;

import se.fredin.gdxtensions.assetmanagement.Assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Array;

/**
 * Handles playback of all audio in the game.
 * @author Johan Fredin
 *
 */
public class AudioUtils {
	
	/** The path that should be used to access music, starting value will be "sound/music/" */
	public static String musicFilesPath = "sound/music/";
	/** The path that should be used to acces sound effects, starting value will be "sound/effects/" */
	public static String soundEffecFilesPath = "sound/effects/";
	
	/** Mute volume */
	private final float VOLUME_MUTE = 0.00f;
	
	/** Max volume */
	private final float VOLUME_MAX = 1.00f;
	
	/** Our singleton instance */
	private static AudioUtils audioUtils;
	
	/** Our sound effects */
	private Map<String, Sound> soundEffects;
	
	
	/** Our music tracks */
	private Map<String, Music> tracks;
	
	private boolean isMute;
	
	/** 
	 * Create a new instance if instance is null, if not retrieve our instance 
	 * @return our instance
	 * */
	public static AudioUtils getInstance() {
		if(audioUtils == null) {
			audioUtils = new AudioUtils();
		}
		return audioUtils;
	}
	
	public static void setPathsToBothMusicAndSoundEffects(String musicPath, String soundEffectsPath) {
		musicFilesPath = musicPath;
		soundEffecFilesPath = soundEffectsPath;
	}
	
	public void setMute(boolean isMute) {
		this.isMute = isMute;
	}
	
	public boolean isMute() {
		return isMute;
	}
	
	/**
	 * Plays the given sound effect if mute is not enabled
	 * @param name name of the sound effect in our map.
	 */
	public void playSoundFX(String name) {
		if(!isMute) {
			soundEffects.get(name).play();
		}
	}
	
	/**
	 * Resets the volume of all the tracks to max volume
	 */
	public void resetVolume() {
		for(Music music : tracks.values()) {
			music.setVolume(VOLUME_MAX);
		}
	}
	
	/**
	 * Creates a volume cross fade effect between two tracks.
	 * @param nameOfTrackToFadeOut the name of the track to fade out
	 * @param nameOfTrackToFadeIn the name of the track to fade in
	 * @param duration the fade duration
	 * @param shouldStopOnceFadingDone whether or not to stop playback of the track we fade out once fading is done
	 */
	public void crossFadeTracks(final String nameOfTrackToFadeOut, final String nameOfTrackToFadeIn, final float duration, final boolean shouldStopOnceFadingDone) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				float timer = 0.0f;
				tracks.get(nameOfTrackToFadeOut).setVolume(VOLUME_MUTE);
				tracks.get(nameOfTrackToFadeIn).setVolume(VOLUME_MAX);
				playMusic(nameOfTrackToFadeIn, true);
				while(timer <= duration) {
					try {
						timer += Gdx.graphics.getDeltaTime();
						tracks.get(nameOfTrackToFadeOut).setVolume(Math.abs(VOLUME_MAX - ((VOLUME_MAX / duration) * timer)));
						tracks.get(nameOfTrackToFadeIn).setVolume(Math.abs((VOLUME_MAX / duration) * timer));
						Thread.sleep(4);
					} catch(Exception e) { 
						e.printStackTrace();
					}
				}
				if(shouldStopOnceFadingDone) {
					tracks.get(nameOfTrackToFadeOut).stop();
				}
			}
		}).start(); 
	}
	
	/**
	 * Fades out the volume of a track
	 * @param nameOfTrackToFadeOut the name of the track to fade out
	 * @param duration the fade duration
	 * @param shouldStopOnceFadingDone whether or not to stop playback of the track we fade out once fading is done
	 */
	public void fadeOutTrack(final String nameOfTrackToFadeOut, final float duration, final boolean shouldStopOnceFadingDone) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				float timer = 0.0f;
				while(timer <= duration) {
					try {
						timer += Gdx.graphics.getDeltaTime();
						tracks.get(nameOfTrackToFadeOut).setVolume(Math.abs(VOLUME_MAX - ((VOLUME_MAX / duration) * timer)));
						Thread.sleep(4);
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
				if(shouldStopOnceFadingDone) {
					tracks.get(nameOfTrackToFadeOut).stop();
				}
			}
		}).start();
	}
	
	
	/**
	 * Stops playing one track and starts another
	 * @param trackToStop the name of the track we want to stop.
	 * @param trackToPlay the name of the track we want to play.
	 */
	public void switchTrack(String trackToStop, String trackToPlay) {
		stopTrack(trackToStop);
		playMusic(trackToPlay, true);
	}
	
	/**
	 * Plays the given track if mute is not enabled.
	 * @param name the name of the track in our map.
	 */
	public void playMusic(String name) {
		if(!isMute) {
			tracks.get(name).play();
		}
	}
	
	/**
	 * Plays the given track if mute is not enabled.
	 * @param name name of the track in our map.
	 * @param loop whether or not we should loop the track.
	 */
	public void playMusic(String name, boolean loop) {
		tracks.get(name).setVolume(VOLUME_MAX);
		tracks.get(name).setLooping(loop);
		playMusic(name);
	}
	
	/**
	 *Plays the given track if mute is not enabled.
	 * @param name name of the track in our map.
	 * @param loop whether or not we should loop the track. 
	 * @param isAlreadyPlaying sometimes the track might already be playing.
	 * When we go back and forth in menu screens for example. Then we don't want to start
	 * playing it again!
	 */
	public void playMusic(String name, boolean loop, boolean isAlreadyPlaying) {
		if(!isAlreadyPlaying) {
			playMusic(name, loop);
		}
	}
	
	/**
	 * Stops playback of all the music tracks
	 */
	public void stopAllMusic() {
		for(Music music : tracks.values()) {
			music.stop();
		}
	}

	/**
	 * Stops playback of given track.
	 * @param trackName the name of the track in our map.
	 */
	public void stopTrack(String trackName) {
		tracks.get(trackName).stop();
	}
	
	/**
	 * Create our singleton instance and populates the tracks and soundEffects hash maps
	 */
	private AudioUtils() {
		this.tracks = new HashMap<String, Music>();
		this.soundEffects = new HashMap<String, Sound>();
	}
	
	
	/**
	 * Lets user add an arbitrary amount of tracks. The method takes for granted that
	 * the musicFilesPath have been set already, will not work otherwise, the names to pass
	 * in should be the final name of the track e.g "mysong.mp3". The method will then add
	 * The absolute path will be musicFilesPath + trackName. This method uses {@link se.fredin.gdxtensions.Assets}
	 * to load the music tracks and therefore those assets HAVE TO have been loaded previously before
	 * attempting to add them here.
	 * @param musicTracks the names of the tracks without the path e.g "mysong.mp3"
	 */
	public void addMusicTracksUsingAssets(String...musicTracks) {
		for(String trackName : musicTracks) {
			tracks.put(trackName, (Music) Assets.getInstance().get(musicFilesPath + trackName));
		}
	}
	
	/**
	 * Lets user add an arbitrary amount of tracks. The method takes for granted that
	 * the musicFilesPath have been set already, will not work otherwise, the names to pass
	 * in should be the final name of the track e.g "mysong.mp3". The method will then add
	 * The absolute path will be musicFilesPath + trackName. This method uses {@link se.fredin.gdxtensions.Assets}
	 * to load the music tracks and therefore those assets HAVE TO have been loaded previously before
	 * attempting to add them here.
	 * @param musicTracks the names of the tracks without the path e.g "mysong.mp3"
	 */
	public void addMusicTracksUsingAssets(Array<String> musicTracks) {
		for(String trackName : musicTracks) {
			tracks.put(trackName, (Music) Assets.getInstance().get(musicFilesPath + "grass.mp3"));
		}
	}
	
	/**
	 * Lets user add an arbitrary amount of tracks. The method takes for granted that
	 * the musicFilesPath have been set already, will not work otherwise, the names to pass
	 * in should be the final name of the track e.g "mysong.mp3". The method will then add
	 * The absolute path will be musicFilesPath + trackName. 
	 * @param musicTracks the names of the tracks without the path e.g "mysong.mp3"
	 */
	public void addMusicTracksUsingGdxAudio(String...musicTracks) {
		for(String trackName : musicTracks) {
			tracks.put(trackName, Gdx.audio.newMusic(Gdx.files.internal(musicFilesPath + trackName)));
		}
	}
	
	/**
	 * Lets user add an arbitrary amount of tracks. The method takes for granted that
	 * the musicFilesPath have been set already, will not work otherwise, the names to pass
	 * in should be the final name of the track e.g "mysong.mp3". The method will then add
	 * The absolute path will be musicFilesPath + trackName.
	 * @param musicTracks the names of the tracks without the path e.g "mysong.mp3"
	 */
	public void addMusicTracksUsingGdxAudio(Array<String> musicTracks) {
		for(String trackName : musicTracks) {
			tracks.put(trackName, Gdx.audio.newMusic(Gdx.files.internal(musicFilesPath + trackName)));
		}
	}
	
	/**
	 * Lets user add an arbitrary amount of sound effects. The method takes for granted that
	 * the soundEffecFilesPath have been set already, will not work otherwise, the names to pass
	 * in should be the final name of the track e.g "myeffect.wav". The absolute path will be 
	 * soundEffecFilesPath + effectName. This method uses {@link se.fredin.gdxtensions.Assets}
	 * to load the sound effects and therefore those assets HAVE TO have been loaded previously before
	 * attempting to add them here.
	 * @param soundEffects the names of the sound effects without the path e.g "myeffect.wav"
	 */
	public void addSoundEffectsUsingAssets(String...soundEffects) {
		for(String soundEffect : soundEffects) {
			this.soundEffects.put(soundEffect, (Sound) Assets.getInstance().get(soundEffecFilesPath + soundEffect));
		}
	}
	
	/**
	 * Lets user add an arbitrary amount of sound effects. The method takes for granted that
	 * the soundEffecFilesPath have been set already, will not work otherwise, the names to pass
	 * in should be the final name of the track e.g "myeffect.wav". The absolute path will be 
	 * soundEffecFilesPath + effectName. This method uses {@link se.fredin.gdxtensions.Assets}
	 * to load the sound effects and therefore those assets HAVE TO have been loaded previously before
	 * attempting to add them here.
	 * @param soundEffects the names of the sound effects without the path e.g "myeffect.wav"
	 */
	public void addSoundEffectsFromAssets(Array<String> soundEffects) {
		for(String soundEffect : soundEffects) {
			this.soundEffects.put(soundEffect, (Sound) Assets.getInstance().get(soundEffecFilesPath + soundEffect));
		}
	}
	
	/**
	 * Lets user add an arbitrary amount of sound effects. The method takes for granted that
	 * the soundEffecFilesPath have been set already, will not work otherwise, the names to pass
	 * in should be the final name of the track e.g "myeffect.wav". The absolute path will be 
	 * soundEffecFilesPath + effectName.
	 * @param soundEffects the names of the sound effects without the path e.g "myeffect.wav"
	 */
	public void addSoundEffectsUsingGDXAudio(String...soundEffects) {
		for(String soundEffect : soundEffects) {
			this.soundEffects.put(soundEffect, Gdx.audio.newSound(Gdx.files.internal(soundEffecFilesPath + soundEffect)));
		}
	}
	
	/**
	 * Lets user add an arbitrary amount of sound effects. The method takes for granted that
	 * the soundEffecFilesPath have been set already, will not work otherwise, the names to pass
	 * in should be the final name of the track e.g "myeffect.wav". The absolute path will be 
	 * soundEffecFilesPath + effectName. This method uses {@link se.fredin.gdxtensions.Assets}
	 * to load the sound effects and therefore those assets HAVE TO have been loaded previously before
	 * attempting to add them here.
	 * @param soundEffects the names of the sound effects without the path e.g "myeffect.wav"
	 */
	public void addSoundEffectsUsingGDXAudio(Array<String> soundEffects) {
		for(String soundEffect : soundEffects) {
			this.soundEffects.put(soundEffect, Gdx.audio.newSound(Gdx.files.internal(soundEffecFilesPath + soundEffect)));
		}
	}
	

}