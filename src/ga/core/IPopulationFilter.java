package ga.core;


/**
 * �W�c�̒��������̏����𖞂����݂̂̂�I�яo�����߂ɗp����t�B���^�D
 * {@link #accept(int, IIndividual)}���\�b�h�ɁC�̂��t�B���^��ʉ߂�������������C�R�[���o�b�N���Ďg�p����D
 * �g�p���TPopulationStatistics���Q�Ƃ��Ă��������D
 * @since 98
 * @author hmkz
 */
public interface IPopulationFilter {
	/**
	 * �����Ŏw�肳�ꂽ�̂����̃t�B���^��ʉ߂��邩�ǂ����𔻒肷��D
	 * @param index �̂̃C���f�b�N�X
	 * @param ind ���̃t�B���^��ʉ߂��邩�ǂ���������󂯂�́D
	 * @return �̂��t�B���^��ʉ߂���ꍇ��true�C�����łȂ����false�D
	 * @since 98
	 */
	public boolean accept(int index, IIndividual ind);
}

