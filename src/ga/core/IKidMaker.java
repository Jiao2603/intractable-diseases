package ga.core;


/**
 * �̐�����i�����C�ˑR�ψفCetc.�j�͂��̃C���^�[�t�F�[�X���������邱�ƁD
 * @since 2
 * @author yamhan
 */
public interface IKidMaker {

	/**
	 * �̐����ɕK�v�Ȑe�̂̐���Ԃ��D<BR>
	 * @return �̐����ɕK�v�Ȑe�̂̐�
	 * @since 2
	 */
	int getNoOfParents();
	
	/**
	 * �̐����Ɏg�p�����e�̂��Z�b�g����D
	 * �n���ׂ��e�̂̐���{@link #getNoOfParents()}�œ��邱�ƁD
	 * @param parents �e��
	 * @throws IllegalArgumentException parents�̗v�f����getNoOfParents�̖߂�l�ƈقȂ�Ƃ��Dparents��null���܂܂��Ƃ��D
	 * ���̃C���^�[�t�F�[�X�̎����҂͂��̑��ɂ���O�𓊂��������ǉ����Ă悢�D
	 * @since 2
	 */
	void setParents(IIndividual[] parents);
	
	/**
	 * setParents�Őݒ肳�ꂽ�e�̂���noOfKids�̎q�̂𐶐�����D
	 * �������ꂽ�q�̂́C�z��kids�֑������ĕԋp�����D
	 * @param noOfKids �������ׂ��q�̂̐�
	 * @param kids �������ꂽ�q�̂��󂯎��z��D�z��̊e�v�f��IIndividual�����������N���X�̃C���X�^���X�ŏ���������Ă��Ȃ���΂Ȃ�Ȃ��D
	 * @throws IllegalArgumentException noOfKids > kids.length�̂Ƃ��Dkids��null���܂܂��Ƃ��D
	 * ���̃C���^�[�t�F�[�X�̎����҂͂��̑��ɂ���O�𓊂��������ǉ����Ă悢�D
	 * @since 2
	 */
	void doIt(int noOfKids, IIndividual[] kids);
	
}
