package ga.util;


/**
 * �����_���ȑg�ݍ��킹������
 * @since 2
 * @author isao
 */
public class TRandomCombination {
	
	/** ��Ɨ̈�D0�`n-1�܂ł̐������i�[����Ă��� */
	private int[] fWork;
	
	/**
	 * �R���X�g���N�^
	 * @since 2
	 * @author isao
	 */
	public TRandomCombination() {
		fWork = new int [1];
		fWork[0] = 0;
	}
	
	/**
	 * 0�`n-1�̐����̂����C�d�����������Ƀ����_����array.length��I�����āC
	 * array�Ɋi�[���ĕԂ��D
	 * n == array.length�̏ꍇ�Carray�ɂ́C0�`n-1�̃����_���ȏ��񂪊i�[�����D
	 * @param n ���̓p�����[�^
	 * @param array ����
	 * @since 2
	 * @author isao
	 */
	public void doIt(int n, int array[]) {
		setSize(n);
		TMyRandom rand = TMyRandom.getInstance();
		for (int i = 0; i < array.length; ++i) {
			int j = rand.getInteger(i, n - 1);
			int tmp = fWork[i];
			fWork[i] = fWork[j];
			fWork[j] = tmp;
			array[i] = fWork[i];
		}
	}
	
	/**
	 * ��Ɨ̈�̑傫����ύX����D
	 * @param size ��Ɨ̈�̑傫��
	 * @since 2
	 * @author isao
	 */
	private void setSize(int size) {
		if (fWork.length == size)
			return;
		fWork = new int [size];
		for (int i = 0; i < size; ++i)
			fWork[i] = i;
	}

}
