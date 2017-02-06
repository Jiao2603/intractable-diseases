package ga.core;


import ga.core.IIndividual;

/**
 * �W�c�̓�����Ԃ����j�^�����O���邽�߂̃��j�^�E�C���^�[�t�F�[�X
 * @since 2
 * @author yamhan
 */
public interface IMonitor {
	
	/**
	 * ���M���O���J�n����D�K�v�ɉ����āC�X�g���[�������I�[�v�����邱�ƁD
	 * @param baseDir ���M���O��̃t�H���_
	 * @since 2
	 */
	void startMonitoring(String baseDir);
	
	/**
	 * ���O�o�́D
	 * @param iteration ���㐔
	 * @param evalCount �]����
	 * @param bestIndex �ŗǌ̂̔z��ԍ�
	 * @param average �W�c�̕]���l�̕���
	 * @param pop �W�c
	 * @param parents ���̐����ɎQ�������e
	 * @param kids �������ꂽ�S�Ă̎q
	 * @since 2
	 */
	void output(long iteration, long evalCount, int bestIndex,
                double average, ga.core.TPopulation pop,
                IIndividual[] parents, IIndividual[] kids);
	
	/**
	 * ���M���O���I������D
	 * @since 2
	 */
	void endMonitoring();
											
}
