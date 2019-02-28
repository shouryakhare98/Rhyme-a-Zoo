package com.example.shouryakhare.rhyme_a_zoo;

/**
 * Class that stores and provides resource ID
 */
public class IDProvider {

    private int[] thumbnails = {
            R.drawable.thumbnail_001, R.drawable.thumbnail_002, R.drawable.thumbnail_004, R.drawable.thumbnail_006, R.drawable.thumbnail_008,
            R.drawable.thumbnail_009, R.drawable.thumbnail_012, R.drawable.thumbnail_013, R.drawable.thumbnail_014, R.drawable.thumbnail_027,
            R.drawable.thumbnail_028, R.drawable.thumbnail_029, R.drawable.thumbnail_031, R.drawable.thumbnail_035, R.drawable.thumbnail_037,
            R.drawable.thumbnail_040, R.drawable.thumbnail_041, R.drawable.thumbnail_042, R.drawable.thumbnail_043, R.drawable.thumbnail_047,
            R.drawable.thumbnail_050, R.drawable.thumbnail_051, R.drawable.thumbnail_054, R.drawable.thumbnail_055, R.drawable.thumbnail_056,
            R.drawable.thumbnail_057, R.drawable.thumbnail_061, R.drawable.thumbnail_062, R.drawable.thumbnail_063, R.drawable.thumbnail_065,
            R.drawable.thumbnail_016, R.drawable.thumbnail_073, R.drawable.thumbnail_075, R.drawable.thumbnail_076, R.drawable.thumbnail_077,
            R.drawable.thumbnail_080, R.drawable.thumbnail_082, R.drawable.thumbnail_084, R.drawable.thumbnail_015, R.drawable.thumbnail_010,
            R.drawable.thumbnail_088, R.drawable.thumbnail_089, R.drawable.thumbnail_092, R.drawable.thumbnail_093, R.drawable.thumbnail_095,
            R.drawable.thumbnail_097, R.drawable.thumbnail_098, R.drawable.thumbnail_103, R.drawable.thumbnail_104, R.drawable.thumbnail_105,
            R.drawable.thumbnail_106, R.drawable.thumbnail_107, R.drawable.thumbnail_108, R.drawable.thumbnail_109, R.drawable.thumbnail_110,
            R.drawable.thumbnail_111, R.drawable.thumbnail_112, R.drawable.thumbnail_113, R.drawable.thumbnail_114, R.drawable.thumbnail_115,
            R.drawable.thumbnail_116, R.drawable.thumbnail_117, R.drawable.thumbnail_118, R.drawable.thumbnail_119, R.drawable.thumbnail_120,
            R.drawable.thumbnail_121, R.drawable.thumbnail_122, R.drawable.thumbnail_123, R.drawable.thumbnail_124, R.drawable.thumbnail_125,
            R.drawable.thumbnail_126, R.drawable.thumbnail_127, R.drawable.thumbnail_128, R.drawable.thumbnail_129, R.drawable.thumbnail_130,
            R.drawable.thumbnail_131, R.drawable.thumbnail_132, R.drawable.thumbnail_133, R.drawable.thumbnail_134, R.drawable.thumbnail_135,
            R.drawable.thumbnail_136, R.drawable.thumbnail_137, R.drawable.thumbnail_138, R.drawable.thumbnail_139, R.drawable.thumbnail_140,
            R.drawable.thumbnail_141, R.drawable.thumbnail_142, R.drawable.thumbnail_143, R.drawable.thumbnail_144, R.drawable.thumbnail_145,
            R.drawable.thumbnail_146, R.drawable.thumbnail_147, R.drawable.thumbnail_148, R.drawable.thumbnail_149, R.drawable.thumbnail_150,
            R.drawable.thumbnail_151, R.drawable.thumbnail_152, R.drawable.thumbnail_153, R.drawable.thumbnail_154, R.drawable.thumbnail_155,
            R.drawable.thumbnail_156, R.drawable.thumbnail_157, R.drawable.thumbnail_158, R.drawable.thumbnail_159, R.drawable.thumbnail_160,
            R.drawable.thumbnail_161, R.drawable.thumbnail_162, R.drawable.thumbnail_163, R.drawable.thumbnail_164, R.drawable.thumbnail_165,
            R.drawable.thumbnail_166, R.drawable.thumbnail_167, R.drawable.thumbnail_168, R.drawable.thumbnail_169, R.drawable.thumbnail_170,
            R.drawable.thumbnail_171, R.drawable.thumbnail_172, R.drawable.thumbnail_173, R.drawable.thumbnail_174, R.drawable.thumbnail_175
    };

    private int[] illustrations = {
            R.drawable.rhyme001_illustration, R.drawable.rhyme002_illustration, R.drawable.rhyme004_illustration, R.drawable.rhyme006_illustration, R.drawable.rhyme008_illustration,
            R.drawable.rhyme009_illustration, R.drawable.rhyme012_illustration, R.drawable.rhyme013_illustration, R.drawable.rhyme014_illustration, R.drawable.rhyme027_illustration,
            R.drawable.rhyme028_illustration, R.drawable.rhyme029_illustration, R.drawable.rhyme031_illustration, R.drawable.rhyme035_illustration, R.drawable.rhyme037_illustration,
            R.drawable.rhyme040_illustration, R.drawable.rhyme041_illustration, R.drawable.rhyme042_illustration, R.drawable.rhyme043_illustration, R.drawable.rhyme047_illustration,
            R.drawable.rhyme050_illustration, R.drawable.rhyme051_illustration, R.drawable.rhyme054_illustration, R.drawable.rhyme055_illustration, R.drawable.rhyme056_illustration,
            R.drawable.rhyme057_illustration, R.drawable.rhyme061_illustration, R.drawable.rhyme062_illustration, R.drawable.rhyme063_illustration, R.drawable.rhyme065_illustration,
            R.drawable.rhyme015_illustration, R.drawable.rhyme073_illustration, R.drawable.rhyme075_illustration, R.drawable.rhyme076_illustration, R.drawable.rhyme077_illustration,
            R.drawable.rhyme080_illustration, R.drawable.rhyme082_illustration, R.drawable.rhyme084_illustration, R.drawable.rhyme015_illustration, R.drawable.rhyme010_illustration,
            R.drawable.rhyme088_illustration, R.drawable.rhyme089_illustration, R.drawable.rhyme092_illustration, R.drawable.rhyme093_illustration, R.drawable.rhyme095_illustration,
            R.drawable.rhyme097_illustration, R.drawable.rhyme098_illustration, R.drawable.rhyme103_illustration, R.drawable.rhyme104_illustration, R.drawable.rhyme105_illustration,
            R.drawable.rhyme106_illustration, R.drawable.rhyme107_illustration, R.drawable.rhyme108_illustration, R.drawable.rhyme109_illustration, R.drawable.rhyme110_illustration,
            R.drawable.rhyme111_illustration, R.drawable.rhyme112_illustration, R.drawable.rhyme113_illustration, R.drawable.rhyme114_illustration, R.drawable.rhyme115_illustration,
            R.drawable.rhyme116_illustration, R.drawable.rhyme117_illustration, R.drawable.rhyme118_illustration, R.drawable.rhyme119_illustration, R.drawable.rhyme120_illustration,
            R.drawable.rhyme121_illustration, R.drawable.rhyme122_illustration, R.drawable.rhyme123_illustration, R.drawable.rhyme124_illustration, R.drawable.rhyme125_illustration,
            R.drawable.rhyme126_illustration, R.drawable.rhyme127_illustration, R.drawable.rhyme128_illustration, R.drawable.rhyme129_illustration, R.drawable.rhyme130_illustration,
            R.drawable.rhyme131_illustration, R.drawable.rhyme132_illustration, R.drawable.rhyme133_illustration, R.drawable.rhyme134_illustration, R.drawable.rhyme135_illustration,
            R.drawable.rhyme136_illustration, R.drawable.rhyme137_illustration, R.drawable.rhyme138_illustration, R.drawable.rhyme139_illustration, R.drawable.rhyme140_illustration,
            R.drawable.rhyme141_illustration, R.drawable.rhyme142_illustration, R.drawable.rhyme143_illustration, R.drawable.rhyme144_illustration, R.drawable.rhyme145_illustration,
            R.drawable.rhyme146_illustration, R.drawable.rhyme147_illustration, R.drawable.rhyme148_illustration, R.drawable.rhyme149_illustration, R.drawable.rhyme150_illustration,
            R.drawable.rhyme151_illustration, R.drawable.rhyme152_illustration, R.drawable.rhyme153_illustration, R.drawable.rhyme154_illustration, R.drawable.rhyme155_illustration,
            R.drawable.rhyme156_illustration, R.drawable.rhyme157_illustration, R.drawable.rhyme158_illustration, R.drawable.rhyme159_illustration, R.drawable.rhyme160_illustration,
            R.drawable.rhyme161_illustration, R.drawable.rhyme162_illustration, R.drawable.rhyme163_illustration, R.drawable.rhyme164_illustration, R.drawable.rhyme165_illustration,
            R.drawable.rhyme166_illustration, R.drawable.rhyme167_illustration, R.drawable.rhyme168_illustration, R.drawable.rhyme169_illustration, R.drawable.rhyme170_illustration,
            R.drawable.rhyme171_illustration, R.drawable.rhyme172_illustration, R.drawable.rhyme173_illustration, R.drawable.rhyme174_illustration, R.drawable.rhyme175_illustration
    };

    private int[] rhymes = {
            R.raw.rhyme_001, R.raw.rhyme_002, R.raw.rhyme_004, R.raw.rhyme_006, R.raw.rhyme_008,
            R.raw.rhyme_009, R.raw.rhyme_012, R.raw.rhyme_013, R.raw.rhyme_014, R.raw.rhyme_027,
            R.raw.rhyme_028, R.raw.rhyme_029, R.raw.rhyme_031, R.raw.rhyme_035, R.raw.rhyme_037,
            R.raw.rhyme_040, R.raw.rhyme_041, R.raw.rhyme_042, R.raw.rhyme_043, R.raw.rhyme_047,
            R.raw.rhyme_050, R.raw.rhyme_051, R.raw.rhyme_054, R.raw.rhyme_055, R.raw.rhyme_056,
            R.raw.rhyme_057, R.raw.rhyme_061, R.raw.rhyme_062, R.raw.rhyme_063, R.raw.rhyme_065,
            R.raw.rhyme_070, R.raw.rhyme_073, R.raw.rhyme_075, R.raw.rhyme_076, R.raw.rhyme_077,
            R.raw.rhyme_080, R.raw.rhyme_082, R.raw.rhyme_084, R.raw.rhyme_086, R.raw.rhyme_087,
            R.raw.rhyme_088, R.raw.rhyme_089, R.raw.rhyme_092, R.raw.rhyme_093, R.raw.rhyme_095,
            R.raw.rhyme_097, R.raw.rhyme_098, R.raw.rhyme_103, R.raw.rhyme_104, R.raw.rhyme_105,
            R.raw.rhyme_106, R.raw.rhyme_107, R.raw.rhyme_108, R.raw.rhyme_109, R.raw.rhyme_110,
            R.raw.rhyme_111, R.raw.rhyme_112, R.raw.rhyme_113, R.raw.rhyme_114, R.raw.rhyme_115,
            R.raw.rhyme_116, R.raw.rhyme_117, R.raw.rhyme_118, R.raw.rhyme_119, R.raw.rhyme_120,
            R.raw.rhyme_121, R.raw.rhyme_122, R.raw.rhyme_123, R.raw.rhyme_124, R.raw.rhyme_125,
            R.raw.rhyme_126, R.raw.rhyme_127, R.raw.rhyme_128, R.raw.rhyme_129, R.raw.rhyme_130,
            R.raw.rhyme_131, R.raw.rhyme_132, R.raw.rhyme_133, R.raw.rhyme_134, R.raw.rhyme_135,
            R.raw.rhyme_136, R.raw.rhyme_137, R.raw.rhyme_138, R.raw.rhyme_139, R.raw.rhyme_140,
            R.raw.rhyme_141, R.raw.rhyme_142, R.raw.rhyme_143, R.raw.rhyme_144, R.raw.rhyme_145,
            R.raw.rhyme_146, R.raw.rhyme_147, R.raw.rhyme_148, R.raw.rhyme_149, R.raw.rhyme_150,
            R.raw.rhyme_151, R.raw.rhyme_152, R.raw.rhyme_153, R.raw.rhyme_154, R.raw.rhyme_155,
            R.raw.rhyme_156, R.raw.rhyme_157, R.raw.rhyme_158, R.raw.rhyme_159, R.raw.rhyme_160,
            R.raw.rhyme_161, R.raw.rhyme_162, R.raw.rhyme_163, R.raw.rhyme_164, R.raw.rhyme_165,
            R.raw.rhyme_166, R.raw.rhyme_167, R.raw.rhyme_168, R.raw.rhyme_169, R.raw.rhyme_170,
            R.raw.rhyme_171, R.raw.rhyme_172, R.raw.rhyme_173, R.raw.rhyme_174, R.raw.rhyme_175
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
