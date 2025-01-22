import speech_recognition as sr
from pydub import AudioSegment

def convert_audio_to_wav(input_audio_path, output_wav_path):
    audio = AudioSegment.from_file(input_audio_path)
    audio.export(output_wav_path, format="wav")

def recognize_speech_from_audio(audio_path):
    recognizer = sr.Recognizer()
    with sr.AudioFile(audio_path) as source:
        audio_data = recognizer.record(source)
        try:
            text = recognizer.recognize_google(audio_data)
            return text
        except sr.UnknownValueError:
            return "Google Speech Recognition could not understand the audio"
        except sr.RequestError as e:
            return f"Could not request results from Google Speech Recognition service; {e}"

def main():
    input_audio_path = input("Enter the path to the audio file: ")
    output_wav_path = "converted_audio.wav"

    # Convert audio to WAV format if necessary
    if not input_audio_path.lower().endswith(".wav"):
        convert_audio_to_wav(input_audio_path, output_wav_path)
        audio_path = output_wav_path
    else:
        audio_path = input_audio_path

    print("Recognizing speech from audio...")
    text = recognize_speech_from_audio(audio_path)
    print("Recognized Text:")
    print(text)

if __name__ == "__main__":
    main()
