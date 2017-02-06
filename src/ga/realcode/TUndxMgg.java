package ga.realcode;

import ga.core.IIndividual;
import ga.core.IKidMakerFactory;
import ga.core.TPopulation;
import ga.mgg.*;
import ga.realcode.TUndxKidMakerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * UNDX+MGG�N���X
 * @author isao
 *
 */
public class TUndxMgg {
	
	/** �W�c */
	private TPopulation fPopulation;
	
	/** MGG�N���X */
	private TMgg fMgg;
	
	/**
	 * �R���X�g���N�^
	 * @param isMinimization �ŏ�����肩�H
	 * @param noOfParameters ������
	 * @param populationSize �W�c�T�C�Y
	 * @param noOfCrossovers ������
	 * @param useRouletteSelection �����I���ɂ����āC���[���b�g�I�����g�����H�@false�ɂ���ƁCBest2���I�������悤�ɂȂ�D
	 */
	public TUndxMgg(boolean isMinimization, int noOfParameters, int populationSize, int noOfCrossovers) {
		ISelectionForReproductionFactory selectionForReproductionFactory = new TRandomSelectionForReproductionFactory();
		IKidMakerFactory kidMakerFactory = new TUndxKidMakerFactory(0.5, 0.35);
		ISelectionForSurvivalFactory selectionForSuvivalFactory = new TBestAndRankBasedRouletteSelectionFactory(isMinimization);
		TRealNumberPopulationMaker popMaker = new TRealNumberPopulationMaker(populationSize, noOfParameters);
		fPopulation = popMaker.createInitialPopulation();
		fMgg = new TMgg(selectionForReproductionFactory, kidMakerFactory, noOfCrossovers, 
				             selectionForSuvivalFactory, popMaker.getIndividualFactory());
		fMgg.setPopulationAndIteration(fPopulation, 0);
	}
	
	/**
	 * ���]���̌̂���\������鏉��W�c��Ԃ��D
	 * @return ����W�c
	 */
	public List<TRealNumberIndividual> getInitialPopulation() {
		List<TRealNumberIndividual> initPop = new ArrayList<TRealNumberIndividual>();
		for ( int i = 0; i < fPopulation.getSize(); ++i) {
			initPop.add( ( TRealNumberIndividual)fPopulation.getIndividual( i));
		}
		return initPop;
	}
	
	
//	public TRealNumberIndividual[] getInitialPopulation() {
//		TRealNumberIndividual[] initPop = new TRealNumberIndividual [fPopulation.getSize()];
//		for (int i = 0; i < fPopulation.getSize(); ++i) {
//			initPop[i] = (TRealNumberIndividual)fPopulation.getIndividual(i);
//		}
//		return initPop;
//	}

	/**
	 * �W�c����I�����ꂽ�e�̂ƁC�e�̂��琶�����ꂽ���]���̎q�̂���\�������Ƒ��W�c��Ԃ��D
	 * @return �Ƒ��W�c
	 */
	public List<TRealNumberIndividual> selectParentsAndMakeKids() {
		IIndividual[] tmp = fMgg.selectParentsAndMakeKids();
		List<TRealNumberIndividual> family = new ArrayList<TRealNumberIndividual>();
		for ( int i = 0; i < tmp.length; ++i) {
			family.add( ( TRealNumberIndividual)tmp[ i]);
		}
		return family;
	}
//	public TRealNumberIndividual[] selectParentsAndMakeKids() {
//		IIndividual[] tmp = fMgg.selectParentsAndMakeKids();
//		TRealNumberIndividual[] family = new TRealNumberIndividual [tmp.length];
//		for (int i = 0; i < tmp.length; ++i) {
//			family[i] = (TRealNumberIndividual)tmp[i];
//		}
//		return family;
//	}

	/**
	 * �����I�����s������̏W�c��Ԃ��D
	 * @return �W�c
	 */
	public List<TRealNumberIndividual> doSelectionForSurvival() {
		fMgg.doSelectionForSurvival();
		List<TRealNumberIndividual> population = new ArrayList<TRealNumberIndividual>();
		for (int i = 0; i < fPopulation.getSize(); ++i) {
			population.add( ( TRealNumberIndividual)fPopulation.getIndividual( i));
		}
		return population;
	}
//	public TRealNumberIndividual[] doSelectionForSurvival() {
//		fMgg.doSelectionForSurvival();
//		TRealNumberIndividual[] population = new TRealNumberIndividual [fPopulation.getSize()];
//		for (int i = 0; i < fPopulation.getSize(); ++i) {
//			population[i] = (TRealNumberIndividual)fPopulation.getIndividual(i);
//		}
//		return population;
//	}

	/**
	 * �W�c���̍ŗǌ̂�Ԃ��D
	 * @return �W�c���̍ŗǌ�
	 */
	public TRealNumberIndividual getBestIndividual() {
		return (TRealNumberIndividual)fMgg.getBestIndividual();
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
