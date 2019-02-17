package com.example.shouryakhare.rhyme_a_zoo;

/**
 * Class that stores and provides resource ID
 */
public class IDProvider {

    private int[] thumbnails = {
            R.drawable.thumbnail_001, R.drawable.thumbnail_103, R.drawable.thumbnail_055, R.drawable.thumbnail_104,
            R.drawable.thumbnail_002, R.drawable.thumbnail_092
    };

    private int[] illustrations = {
            R.drawable.rhyme001_illustration, R.drawable.rhyme103_illustration, R.drawable.rhyme055_illustration,
            R.drawable.rhyme104_illustration, R.drawable.rhyme002_illustration, R.drawable.rhyme092_illustration
    };

    private int[] rhymes = {
            R.raw.rhyme_001, R.raw.rhyme_103, R.raw.rhyme_055, R.raw.rhyme_104, R.raw.rhyme_002, R.raw.rhyme_092
    };

    private int[][] questions = {
            {},{}
    };

    private int[][][] options = {
            {{},{}},{{},{}}
    };

    private int[][] answers = {
            {},{}
    };

    public int getThumbnailId(int index) {
        return this.thumbnails[index];
    }

    public int getThumbnailArrayLength() {
        return this.thumbnails.length;
    }

    public int getIllustrationId(int index) {
        return this.illustrations[index];
    }

    public int getIllustrationArrayLength() {
        return this.illustrations.length;
    }

    public int getRhymesId(int index) {
        return this.rhymes[index];
    }

    public int getRhymesArrayLength() {
        return this.rhymes.length;
    }

    // TODO: Make sure the below three functions work
    public int[] getQuestionAudioId(int index) {
        return this.questions[index];
    }

    public int[] getOptionAudioId(int rhymeIndex, int questionIndex) {
        return this.options[rhymeIndex][questionIndex];
    }

    public int[] getAnswerAudioId(int index) {
        return this.answers[index];
    }
}
