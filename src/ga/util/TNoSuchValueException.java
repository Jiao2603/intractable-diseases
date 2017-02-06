package ga.util;

/**
 * �v�����ꂽ�l�����݂����C����ɕԂ����Ƃ��ł���K�؂Ȓl���Ȃ��󋵂œ��������O�D
 * <p>
 * ���̗�O��RuntimeException���p�����Ă��邽�߁C�L���b�`���Ȃ��Ă��R���p�C���G���[�ɂ͂Ȃ�܂���D
 * <p>
 * ���̗�O�́C�v�����ꂽ�l���_���I�ɑ��݂����Ȃ��悤�ȏ󋵂��������܂��D
 * �Ⴆ�΁C�v�f��0�̏W���̕��ϒl���v�����ꂽ�ꍇ�Ȃǂɂ��̗�O�𓊂��܂��D
 * <p>
 * �ꎞ�I�ȏ�Q��Z�L�����e�B��̐����Ȃǂɂ��v�����ۂ��������邽�߂ɂ��̗�O���g���Ă͂����܂���D
 * �Ⴆ�΁C�l�b�g���[�N�̏�Q��t�@�C���̃A�N�Z�X�����Ȃǂ̗��R�ɂ��
 * �l���ǂݏo���Ȃ��悤�ȏꍇ�ɂ́C���̗�O�𓊂��Ă͂����܂���D
 * �����̗p�r�ɂ�JDK�ɂ���Ȃ�̗�O���p�ӂ���Ă��܂��̂ł�����𗘗p���Ă��������D
 * @since 74
 * @author hmkz
 */
public class TNoSuchValueException extends RuntimeException {
	/**
	 * ��̃��b�Z�[�W����������O�𐶐�����
	 * @since 74
	 * @author hmkz
	 */
	public TNoSuchValueException() {}

	/**
	 * �w�肳�ꂽ���b�Z�[�W����������O�𐶐�����
	 * @param message ��O���b�Z�[�W
	 * @since 74
	 * @author hmkz
	 */
	public TNoSuchValueException(String message) {
		super(message);
	}
}
