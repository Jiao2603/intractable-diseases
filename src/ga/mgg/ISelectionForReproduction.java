package ga.mgg;

import ga.core.IIndividual;
import ga.core.TPopulation;

/**
 * �����I���C���^�[�t�F�[�X
 * @since 2
 * @author isao
 */
public interface ISelectionForReproduction {
	
	/**
	 * �����I�����s�Ȃ�
	 * @param pop �W�c
	 * @param parentIndices �I�����ꂽ�e�̂̏W�c���ɂ�����ʒu
	 * @param parents �I�����ꂽ�e�̂̃R�s�[
	 * @since 2
	 */
	public void doIt(TPopulation pop, int[] parentIndices, IIndividual[] parents);

}
