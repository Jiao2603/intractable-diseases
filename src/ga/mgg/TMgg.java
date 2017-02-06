package ga.mgg;

import ga.core.*;

/**
 * SOARS�p�����ヂ�f��Minimal Generation Gap (MGG)
 * @since 2
 * @author yamhan, isao
 */
public class TMgg {
	
	/** �̃t�@�N�g�� */
	private IIndividualFactory fIndividualFactory;
	
	/** �q�̐�����t�@�N�g�� */
	private IKidMakerFactory fKidMakerFactory;
	
	/** �����I����t�@�N�g�� */
	private ISelectionForReproductionFactory fSelectionForReproductionFactory;
	
	/** �����I����t�@�N�g�� */
	private ISelectionForSurvivalFactory fSelectionForSuvivalFactory;
		
	/** ���݂̔����� */
	private long fIteration;

	/** ������ */
	private int fNoOfCrossovers;
	
	/** �W�c */
	private TPopulation fPopulation;
	
	/** �q�̐����� */
	private IKidMaker fKidMaker;
	
	/** �e�̂̓Y���� */		
	private int[] fParentIndices;

	/** �e�̂̔z�� */
	private IIndividual[] fParents;

	/** �q�̂̔z�� */	
	private IIndividual[] fKids;
	
	/** �����I���� */
	private ISelectionForReproduction fSelectionForReproduction;
	
	/** �����I���� */
	private ISelectionForSurvival fSelectionForSurvival;
	
	/** �W�c�̓��v */
	private TPopulationStatistics fPopulationStatistics;
	
	/**
	 * �R���X�g���N�^
	 * @param selectionForReproductionFactory �����I����t�@�N�g��
	 * @param kidMakerFactory �̐�����t�@�N�g��
	 * @param noOfCrossovers ������
	 * @param selectionForSurvivalFactory �����I����t�@�N�g�� 
	 * @param individualFactory �̃t�@�N�g��
	 * @since 2
	 * @author yamhan, isao
	 */
	public TMgg(ISelectionForReproductionFactory selectionForReproductionFactory,
							 IKidMakerFactory kidMakerFactory, int noOfCrossovers,
							 ISelectionForSurvivalFactory selectionForSurvivalFactory,
			         IIndividualFactory individualFactory) {
		fNoOfCrossovers = noOfCrossovers;
		fIndividualFactory = individualFactory;
		fKidMakerFactory = kidMakerFactory;
		fSelectionForReproductionFactory = selectionForReproductionFactory;
		fSelectionForSuvivalFactory = selectionForSurvivalFactory;
		fKidMaker = fKidMakerFactory.create();
		fSelectionForReproduction = fSelectionForReproductionFactory.create();
		fSelectionForSurvival = fSelectionForSuvivalFactory.create();
		fIteration = 0;
		fParentIndices = new int [fKidMaker.getNoOfParents()];
		fParents = new IIndividual [fKidMaker.getNoOfParents()];
		for (int i = 0; i < fKidMaker.getNoOfParents(); ++i) {
			fParents[i] = fIndividualFactory.create();
		}
		fKids = new IIndividual[2 * fNoOfCrossovers];
		for(int i = 0; i < fKids.length; i++) {
			fKids[i] = fIndividualFactory.create();
		}
	}
	
	/**
	 * ���݂̔����񐔂�Ԃ��D
	 * @return ������
	 * @since 2
	 * @author yamhan, isao
	 */
	public long getIteration() {
		return fIteration;
	}

	/**
	 * ���݂̔����񐔂�ݒ肷��D
	 * @param itr ������
	 * @since 2
	 * @author yamhan, isao
	 */
	public void setIteration(long itr) {
		fIteration = itr;
	}

	/**
	 * �W�c���̍ŗǌ̂�Ԃ��D
	 * @return �ŗǌ�
	 * @since 2
	 * @author yamhan, isao
	 */
	public IIndividual getBestIndividual() {
		return fPopulationStatistics.getBestIndividual();
	}

	/**
	 * �W�c���̍ŗǌ̂̓Y������Ԃ��D
	 * @return �ŗǌ̂̓Y����
	 * @since 2
	 * @author yamhan, isao
	 */
	public int getBestIndex() {
		return fPopulationStatistics.getBestIndex();
	}


	/**
	 * �ŗǌ̂̕]���l��Ԃ��D
	 * @return �ŗǌ̂̕]���l
	 * @since 2
	 * @author yamhan, isao
	 */
	public double getBestEvaluationValue() {
		return fPopulationStatistics.getBestEvaluationValue();
	}

	/**
	 * �W�c����VALID�Ȍ̂̕��ϕ]���l��Ԃ��D
	 * �W�c����VALID�Ȍ̂�����Ȃ���Ε��ϕ]���l��0�Ƃ���D
	 * @return ���ϕ]���l
	 * @since 2
	 * @author yamhan, isao
	 */
	public double getAverageOfEvaluationValues() {
		return fPopulationStatistics.getAverageOfEvaluationValues();
	}
	
	/**
	 * �q�𐶐����邽�߂̐e��I��������C�����������񐔂������s���Ďq�𕡐���������D
	 * @return �e�Ɛ������ꂽ�q
	 */
	public IIndividual[] selectParentsAndMakeKids() {
		fSelectionForReproduction.doIt(fPopulation, fParentIndices, fParents);
		makeKids(fParents, fKids);
		IIndividual[] family = new IIndividual [fParents.length + fKids.length];
		int index = 0;
		for (int i = 0; i < fParents.length; ++i) {
			family[index] = fParents[i];
			++index;
		}
		for (int i= 0; i < fKids.length; ++i) {
			family[index] = fKids[i];
			++index;
		}
		return family;
	}
	
	/**
	 * �����I�����s���C�����1�i�߂�D
	 */
	public void doSelectionForSurvival() {
		fSelectionForSurvival.doIt(fPopulation, fParentIndices, fParents, fKids);
		fIteration++;
	}

	/**
	 * �W�c��Ԃ��D
	 * @return ���݂̏W�c
	 * @since 2
	 * @author yamhan, isao
	 */
	public TPopulation getPopulation() {
		return fPopulation;
	}
	
	/**
	 * �W�c��ݒ肷��D
	 * ���̂Ƃ��C�����I�ɂ͈ȉ��̏������Ȃ����F
	 * - �����񐔂��O�ɐݒ肷��D
	 * - ������Ԃ̃��O���o�͂���D
	 * - �q�̏W�c���W�c���̂���̂Ɠ����ݒ�ɏ����������D
	 * @param pop �W�c
	 * @since 2
	 * @author yamhan, isao
	 */
	public void setPopulationAndIteration(TPopulation pop, long iteration) throws IllegalArgumentException {
		fPopulation = pop;
		fPopulationStatistics = new TPopulationStatistics(fPopulation, fSelectionForSurvival.isMinimization());
		fIteration = iteration;
		for (int i = 0; i < fParents.length; ++i) {
			fParents[i].copyFrom(fPopulation.getIndividual(0));
		}
		for (int i = 0; i < fKids.length; ++i) {
			fKids[i].copyFrom(fPopulation.getIndividual(0));
		}			
	}
	
	/**
	 * �q�W�c�𐶐����C���������q�W�c��Ԃ��D
	 * @param parents �e�W�c
	 * @since 2
	 * @author yamhan, isao
	 */	
	private void makeKids(IIndividual[] parents, IIndividual[] kids) {
		fKidMaker.setParents(parents);
		fKidMaker.doIt(2 * fNoOfCrossovers, kids);
	}
	
}
