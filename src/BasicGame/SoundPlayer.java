package BasicGame;

import org.newdawn.slick.Sound;
//one entity with one sound

public class SoundPlayer {
	private Sound sound;
	private float pitch = 1f, valume = 0.5f;
	private boolean loop = false;
	public byte play_count = 0;

	public SoundPlayer() {

	}

	public void Sound_Set(Sound sound) {

		Sound_Set(sound, 1f, 0.5f);

	}

	public void Sound_Set(Sound sound, float pitch, float valume) {

		Sound_Set(sound, pitch, valume, false);

	}

	public void Sound_Set(Sound sound, float pitch, float valume, boolean loop) {

		this.pitch = pitch;
		this.valume = valume;
		this.sound = sound;
		this.loop = loop;
		this.play_count = 0;
		

	}

	public void play(float speed, float valume) {
		this.pitch = speed;
		this.valume = valume;
		play();

	}

	public void play(float valume) {
		this.valume = valume;
		play();

	}

	public boolean play() {
		if ((sound != null)) {
			if ((sound.playing() == false) && (play_count == 0)) {
				sound.play(pitch, valume);
				
				play_count += 1;
				
				return true;
			}
		}
		return false;
	}

	public void stop() {
		if (sound != null) {
			sound.stop();
		}

	}
	
	public boolean playing(){
		return this.sound.playing();
	}
}
