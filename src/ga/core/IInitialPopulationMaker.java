package ga.core;


import ga.core.IIndividualFactory;

/**
 * �����W�c������C���^�[�t�F�[�X�D
 * @since 2
 * @author isao
 */
public interface IInitialPopulationMaker {
	
	/**
	 * �����W�c�𐶐����ĕԂ��D
	 * @return �����W�c
	 * @since 2
	 */
	TPopulation createInitialPopulation();
	
	/**
	 * �̃t�@�N�g����Ԃ��D
	 * @return �̃t�@�N�g��
	 */
	IIndividualFactory getIndividualFactory();

}
