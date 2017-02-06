package ga.mgg;

import ga.core.IIndividual;
import ga.core.TPopulation;
import ga.mgg.ISelectionForSurvival;

/**
 * ��ʂQ�̂�I�����鐶���I����D
 * @since 2
 * @author isao
 */
public class TBest2Selection implements ISelectionForSurvival {

	/** �Ƒ� */
	private IIndividual[] fFamily;

	/** �ŏ�����肩�H */
	private boolean fMinimization;
	
	/**
	 * �R���X�g���N�^
	 * @since 2
	 * @author isao
	 */
	public TBest2Selection(boolean isMinimization) {
		fFamily = new IIndividual [1];
		fMinimization = isMinimization;
	}

	/**
	 * @see TBestAndRankBasedRouletteSelection#doIt(TPopulation, int[], IIndividual[], IIndividual[])
	 * @since 2
	 * @author isao
	 */
	public void doIt(TPopulation pop, int[] parentIndices, 
					           IIndividual[] parents, IIndividual[] kids) {
		setFamilySize(2, kids.length);
		registerToFamily(parents, kids);
		sort(fFamily);
		IIndividual bestKid = fFamily[0];
		IIndividual secondBestKid = fFamily[1];
		pop.getIndividual(parentIndices[0]).copyFrom(bestKid);
		pop.getIndividual(parentIndices[1]).copyFrom(secondBestKid);		
	}

	/**
	 * �Ƒ��̃T�C�Y��ݒ肷��D
	 * @param noOfParents �e�̂̐�
	 * @param noOfKids �q�̂̐�
	 * @since 2
	 * @author isao
	 */
	private void setFamilySize(int noOfParents, int noOfKids) {
		if (fFamily.length == noOfParents + noOfKids)
			return;
		fFamily = new IIndividual [noOfParents + noOfKids];
	}
	
	/**
	 * �e�̂Ǝq�̂��Ƒ��ɓo�^����D
	 * @param parents �e�̂̔z��
	 * @param kids �q�̂̔z��
	 * @since 2
	 * @author isao
	 */
	private void registerToFamily(IIndividual[] parents, IIndividual[] kids) {
		int index = 0;
		for (int i = 0; i < 2; ++i) {
			fFamily[index++] = parents[i];
		}
		for (int i = 0; i < kids.length; ++i) {
			fFamily[index++] = kids[i];
		}
	}

	/**
	 * �̔z����\�[�g����
	 * @param array �\�[�g����̔z��
	 * @since 2
	 * @author isao
	 */
	private void sort(IIndividual[] array) {
		for(int i = 0; i < array.length - 1; i++) {
			for(int j = i + 1; j < array.length; j++) {
				if(isABetterThanB(array[j], array[i])) {
					IIndividual tmp = array[i];
					array[i] = array[j];
					array[j] = tmp;					
				}
			}
		}		
	}
	
	/**
	 * ��a�ƌ�b���r����D
	 * @param a ��A
	 * @param b ��B
	 * @param problem ���
	 * @return a����b���D��Ă���ꍇ��true, �����łȂ��Ƃ���false
	 * @since 2
	 * @author isao
	 */
	private boolean isABetterThanB(IIndividual a, IIndividual b) {
		if (a.getStatus() == IIndividual.INVALID)
			return false;
		if (b.getStatus() == IIndividual.INVALID)
			return true;
		if (fMinimization) {
			return a.getEvaluationValue() < b.getEvaluationValue();
		} else {
			return a.getEvaluationValue() > b.getEvaluationValue();
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see ga.ISelectionForSurvival#isMinimization()
	 */
	public boolean isMinimization() {
		return fMinimization;
	}

}
