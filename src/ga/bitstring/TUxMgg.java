package ga.bitstring;

import ga.core.IIndividual;
import ga.core.IKidMakerFactory;
import ga.core.TPopulation;
import ga.mgg.*;
import ga.bitstring.TUxKidMakerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * UX+MGG�N���X
 * @author isao
 *
 */
public class TUxMgg {
	
	/** �W�c */
	private TPopulation fPopulation;
	
	/** MGG */
	private TMgg fMgg;
	
	/**
	 * �R���X�g���N�^
	 * @param isMinimization �ŏ�����肩�H
	 * @param noOfBits �r�b�g��
	 * @param populationSize �W�c�T�C�Y
	 * @param noOfCrossovers ������
	 * @param useRouletteSelection �����I���ɂ����āC���[���b�g�I�����g�����H�@false�ɂ���ƁCBest2���I�������悤�ɂȂ�D
	 */
	public TUxMgg(boolean isMinimization, int noOfBits, int populationSize, int noOfCrossovers, boolean useRouletteSelection) {
		ISelectionForReproductionFactory selectionForReproductionFactory = new TRandomSelectionForReproductionFactory();
		IKidMakerFactory kidMakerFactory = new TUxKidMakerFactory();
		ISelectionForSurvivalFactory selectionForSuvivalFactory = null;
		if (useRouletteSelection) {
			selectionForSuvivalFactory = new TBestAndRankBasedRouletteSelectionFactory(isMinimization);
		} else {
			selectionForSuvivalFactory = new TBest2SelectionFactory(isMinimization);
		}
		TBitStringPopulationMaker popMaker = new TBitStringPopulationMaker(populationSize, noOfBits);
		fPopulation = popMaker.createInitialPopulation();
		fMgg = new TMgg(selectionForReproductionFactory, kidMakerFactory, noOfCrossovers, 
				             selectionForSuvivalFactory, popMaker.getIndividualFactory());
		fMgg.setPopulationAndIteration(fPopulation, 0);
	}
	
	/**
	 * ���]���̌̂���\������鏉���W�c��Ԃ��D
	 * @return �����W�c
	 */
	public List<TBitStringIndividual> getInitialPopulation() {
		List<TBitStringIndividual> initPop = new ArrayList<TBitStringIndividual>();
		for ( int i = 0; i < fPopulation.getSize(); ++i) {
			initPop.add( ( TBitStringIndividual)fPopulation.getIndividual( i));
		}
		return initPop;
	}
//	public TBitStringIndividual[] getInitialPopulation() {
//		TBitStringIndividual[] initPop = new TBitStringIndividual [fPopulation.getSize()];
//		for (int i = 0; i < fPopulation.getSize(); ++i) {
//			initPop[i] = (TBitStringIndividual)fPopulation.getIndividual(i);
//		}
//		return initPop;
//	}

	/**
	 * �W�c����I�����ꂽ�e�̂ƁC�e�̂��琶�����ꂽ���]���̎q�̂���\�������Ƒ��W�c��Ԃ��D
	 * @return �Ƒ��W�c
	 */
	public List<TBitStringIndividual> selectParentsAndMakeKids() {
		IIndividual[] tmp = fMgg.selectParentsAndMakeKids();
		List<TBitStringIndividual> family = new ArrayList<TBitStringIndividual>();
		for ( int i = 0; i < tmp.length; ++i) {
			family.add( ( TBitStringIndividual)tmp[ i]);
		}
		return family;
	}
//	public TBitStringIndividual[] selectParentsAndMakeKids() {
//		IIndividual[] tmp = fMgg.selectParentsAndMakeKids();
//		TBitStringIndividual[] family = new TBitStringIndividual [tmp.length];
//		for (int i = 0; i < tmp.length; ++i) {
//			family[i] = (TBitStringIndividual)tmp[i];
//		}
//		return family;
//	}

	/**
	 * �����I�����s������̏W�c��Ԃ��D
	 * @return �W�c
	 */
	public List<TBitStringIndividual> doSelectionForSurvival() {
		fMgg.doSelectionForSurvival();
		List<TBitStringIndividual> population = new ArrayList<TBitStringIndividual>();
		for ( int i = 0; i < fPopulation.getSize(); ++i) {
			population.add( ( TBitStringIndividual)fPopulation.getIndividual( i));
		}
		return population;
	}
//	public TBitStringIndividual[] doSelectionForSurvival() {
//		fMgg.doSelectionForSurvival();
//		TBitStringIndividual[] population = new TBitStringIndividual [fPopulation.getSize()];
//		for (int i = 0; i < fPopulation.getSize(); ++i) {
//			population[i] = (TBitStringIndividual)fPopulation.getIndividual(i);
//		}
//		return population;
//	}

	/**
	 * �W�c���̍ŗǌ̂�Ԃ��D
	 * @return �W�c���̍ŗǌ�
	 */
	public TBitStringIndividual getBestIndividual() {
		return (TBitStringIndividual)fMgg.getBestIndividual();
	}
	
	/**
	 * �W�c���̍ŗǌ̂̕]���l��Ԃ��D
	 * @return �W�c���̍ŗǌ̂̕]���l
	 */
	public double getBestEvaluationValue() {
		return fMgg.getBestEvaluationValue();
	}
	
	/**
	 * �W�c���̌̂̕]���l�̕��ϒl��Ԃ��D
	 * @return �W�c���̌̂̕]���l�̕��ϒl
	 */
	public double getAverageOfEvaluationValues() {
		return fMgg.getAverageOfEvaluationValues();
	}
	
	/**
	 * ���݂̔�����(���㐔)��Ԃ��D
	 * @return ���݂̔�����(���㐔)
	 */
	public long getIteration() {
		return fMgg.getIteration();
	}

}
