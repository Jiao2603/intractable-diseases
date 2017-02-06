package ga.mgg;

import ga.core.IIndividual;
import ga.core.TPopulation;
import ga.util.TRoulette;
import ga.mgg.ISelectionForSurvival;

/**
 * �ŗǌ̑I���ƃ����N�Ɋ�Â����[���b�g�I����g�ݍ��킹�������I��
 * @since 2
 * @author isao
 */
public class TBestAndRankBasedRouletteSelection implements ISelectionForSurvival {

	/** �Ƒ� */
	private IIndividual[] fFamily;

	/** �ŗǌ̂̓Y���� */
	private final static int BEST_KIDS_INDEX = 0; 

	/** ���[���b�g */
	private TRoulette fRoulette;
	
	/** �ŏ������H */
	private boolean fMinimization;

	public TBestAndRankBasedRouletteSelection(boolean isMinimization) {
		fFamily = new IIndividual [1];
		fRoulette = new TRoulette(1);
		fMinimization = isMinimization;
	}

	/**
	 * @see TBestAndRankBasedRouletteSelection#doIt(IProblem, TPopulation, int[], IIndividual[], IIndividual[])
	 * @since 2
	 * @author isao
	 */
	public void doIt(TPopulation pop, int[] parentIndices, 
	                   IIndividual[] parents, IIndividual[] kids) {
		setFamilySize(2, kids.length);
		registerToFamily(parents, kids);
		sort(fFamily);
		IIndividual bestKid = chooseBestKid(fFamily);
		IIndividual selectedKid = chooseKidByRouletteWheelSelection(fFamily);
		pop.getIndividual(parentIndices[0]).copyFrom(bestKid);
		pop.getIndividual(parentIndices[1]).copyFrom(selectedKid);		
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
		fRoulette.setNoOfSlots(fFamily.length);
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

	/**
	 * �q�W�c���̍ŗǌ̂�Ԃ�
	 * @param kids �q�W�c
	 * @return �ŗǌ�
	 * @since 2
	 * @author isao
	 */
	private IIndividual chooseBestKid(IIndividual[] kids) {
		return kids[BEST_KIDS_INDEX];
	}
	
	/**
	 * ���[���b�g�I���ɂ��C�ŗǌ̂��������q�W�c�̒����烉���_���Ɍ̂���I��
	 * @param kids �q�W�c
	 * @return �I�����ꂽ��
	 * @since 2
	 * @author isao
	 */
	private IIndividual chooseKidByRouletteWheelSelection(IIndividual[] kids) {
		fRoulette.resetCurrentSlotIndex();
		int noOfSurvivalKids = getNoOfAliveKids(kids);
		for(int i = 1; i < noOfSurvivalKids; i++) {
			fRoulette.setValueToSlot((double)(noOfSurvivalKids + 1 - i));
		}
		int selectedKid = fRoulette.doIt() + 1;
		return kids[selectedKid];
	}
	
	/**
	 * �q�W�c�̐������Ă���̐���Ԃ�
	 * @param kids �q�W�c
	 * @return �������Ă���̂̐�
	 * @since 2
	 * @author isao
	 */
	private int getNoOfAliveKids(IIndividual[] kids) {
		int survivalKids = 0;
		for(survivalKids = 0; survivalKids < kids.length; survivalKids++) {
			if(kids[survivalKids].getStatus() == IIndividual.INVALID) {
				break;
			}
		}
		return survivalKids;
	}
	
	/*
	 * (non-Javadoc)
	 * @see ga.ISelectionForSurvival#isMinimization()
	 */
	public boolean isMinimization() {
		return fMinimization;
	}
	
}
