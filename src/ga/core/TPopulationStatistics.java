package ga.core;

import ga.util.TNoSuchValueException;
import ga.core.IIndividual;
import ga.core.IPopulationFilter;
import ga.core.TPopulation;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * �W�c�̓��v���Ƃ�
 * @since 38
 * @author hmkz
 */
public class TPopulationStatistics {

	/** ���v�̕�W�c */
	private TPopulation fPopulation;

	/** �ŏ������H */
	private boolean fMinimization;
	
	/** �\���ɂO�ɋ߂��� */
	public static final double EPS = 1e-30;
	
	/**
	 * �R���X�g���N�^
	 * @param population ���v�̕�W�c
	 * @param isMinimization �ŏ����Ftrue, �ő剻�Ftrue
	 * @since 38
	 */
	public TPopulationStatistics(TPopulation population, boolean isMinimization) {
		fPopulation = population;
		fMinimization = isMinimization;
	}

	/**
	 * �W�c��ݒ肷��D
	 * �W�c���̌̂ɂ�null���܂܂�Ă͂����Ȃ��D
	 * @param population �W�c
	 * @since 38
	 */
	public void	setPopulation(TPopulation population) {
		fPopulation = population;
	}

	/**
	 * �W�c����VALID�Ȍ̂̕��ϕ]���l��Ԃ��D
	 * @return ���ϕ]���l
	 * @throws TNoSuchValueException �W�c����VALID�Ȍ̂�����Ȃ��Ƃ��D
	 * @since 38
	 */
	public double	getAverageOfEvaluationValues() {
		int validPopulationSize = 0;
		double averageOfEvaluationValues = 0.0;
		int populationSize = fPopulation.getSize();
		for(int i = 0; i < populationSize; i++) {
			if (fPopulation.getIndividual(i).getStatus() == IIndividual.VALID) {
				averageOfEvaluationValues += fPopulation.getIndividual(i).getEvaluationValue();
				validPopulationSize++;
			}
		}
		if (validPopulationSize == 0) {
			throw new TNoSuchValueException("No valid individual exists.");
		}
		return averageOfEvaluationValues / validPopulationSize;
	}

	/**
	 * �W�c���̍ŗǌ̂�Ԃ��D
	 * �W�c�T�C�Y��0�ł��邩�C���s�\����1���Ȃ��ꍇ�C�ŗǌ̂͑��݂��Ȃ��D
	 * ���̂Ƃ����̃��\�b�h�͗�O�𓊂���D
	 * @return �ŗǌ�
	 * @throws TNoSuchValueException �W�c����VALID�Ȍ̂�����Ȃ��Ƃ��D
	 * @since 38
	 */
	public IIndividual getBestIndividual() {
		return fPopulation.getIndividual(getBestIndex());
	}

	/**
	 * �W�c���̍ŗǌ̂̓Y������Ԃ��D
	 * �W�c���ɍŗǌ̂����݂��Ȃ��ꍇ�C���̃��\�b�h�͗�O�𓊂���D
	 * @return �ŗǌ̂̓Y����
	 * @throws TNoSuchValueException �W�c����VALID�Ȍ̂�����Ȃ��Ƃ�
	 * @since 38
	 */
	public int getBestIndex() {
		int populationSize = fPopulation.getSize();
		IIndividual best = null;
		int bestIndex = -1;
		for(int i = 0; i < populationSize; i++) {
			IIndividual current = fPopulation.getIndividual(i);
			if (current.getStatus() == IIndividual.INVALID) {
				continue;
			}
			if(best == null || isABetterThanB(current, best)) {
				best = current;
				bestIndex = i;
			}
		}
		if (bestIndex == -1) {
			throw new TNoSuchValueException("Best individual does not exist.");
		}
		return bestIndex;
	}

	/**
	 * �ŗǌ̂̕]���l��Ԃ��D
	 * �W�c���ɍŗǌ̂����݂��Ȃ��ꍇ�C���̃��\�b�h�͗�O�𓊂���D
	 * @return �ŗǌ̂̕]���l
	 * @throws TNoSuchValueException �W�c����VALID�Ȍ̂�����Ȃ��Ƃ�
	 * @since 38
	 */
	public double getBestEvaluationValue() {
		return getBestIndividual().getEvaluationValue();
	}

	/**
	 * �W�c���̌̂�]���l�ɂ��������ĕ��בւ���D
	 * �]���l���ǂ��̂قǐ擪�ɕ��ԁD
	 * INVALID�Ȍ̂�VALID�Ȍ̂������ɂ���D
	 * @since 75
	 */
	public void sort() {
		sort(new Comparator() {
			public int compare(Object o1, Object o2) {
				IIndividual i1 = (IIndividual) o1;
				IIndividual i2 = (IIndividual) o2;
				if (i1.getStatus() == IIndividual.INVALID && i2.getStatus() == IIndividual.VALID) {
					return 1;
				}
				if (i1.getStatus() == IIndividual.VALID && i2.getStatus() == IIndividual.INVALID) {
					return -1;
				}
				return isABetterThanB(i1, i2) ? -1 : isAEqualToB(i1, i2) ? 0 : 1;
			}
		});
	}

	/**
	 * �W�c���̌̂���בւ���
	 * @param c
	 * @since 75
	 */
	public void sort(Comparator c) {
		TPopulation aux = new TPopulation(fPopulation);
		mergeSort(aux, fPopulation, 0, fPopulation.getSize(), 0, c);
	}

	/**
	 * x��i�Ԗڂ�j�Ԗڂ̗v�f����������
	 * @param x
	 * @param i
	 * @param j
	 * @since 75
	 */
	private static void swap(TPopulation x, int i, int j) {
		IIndividual indI = x.getIndividual(i);
		IIndividual indJ = x.getIndividual(j);
		x.setIndividual(i, indJ);
		x.setIndividual(j, indI);
	}

	/**
	 * �v�f�������Ȃ��ꍇ�ɂ̓}�[�W�\�[�g�͓K���Ȃ��D���̒l��菭�Ȃ���Α}���\�[�g��p����D
	 */
	private static final int INSERTIONSORT_THRESHOLD = 7;

	/**
	 * @param src ���̏W�c
	 * @param dest �\�[�g�ς݂̒l������
	 * @param low �\�[�g�͈͂̐擪�̃C���f�b�N�X
	 * @param high �\�[�g�͈̖͂����̃C���f�b�N�X
	 * @param off src�ɂ�����low��high��Ή������邽�߂̃I�t�Z�b�g
	 * @since 75
	 */
	private static void mergeSort(TPopulation src, TPopulation dest, int low, int high, int off, Comparator c) {
		int length = high - low;
		if (length < INSERTIONSORT_THRESHOLD) {
			for (int i = low; i < high; i++) {
				for (int j = i; j > low && c.compare(dest.getIndividual(j-1), dest.getIndividual(j)) > 0; j--) {
					swap(dest, j, j-1);
				}
			}
			return;
		}

		int destLow  = low;
		int destHigh = high;
		low  += off;
		high += off;
		int mid = (low + high) / 2;
		mergeSort(dest, src, low, mid, -off, c);
		mergeSort(dest, src, mid, high, -off, c);

		if (c.compare(src.getIndividual(mid-1), src.getIndividual(mid)) <= 0) {
			for (int i = 0; i < length; i++) {
				dest.setIndividual(i + destLow, src.getIndividual(i + low));
			}
			return;
		}

		for(int i = destLow, p = low, q = mid; i < destHigh; i++) {
			if (q >= high || p < mid && c.compare(src.getIndividual(p), src.getIndividual(q)) <= 0)
				dest.setIndividual(i, src.getIndividual(p++));
			else
				dest.setIndividual(i, src.getIndividual(q++));
		}
	}

	/**
	 * �t�B���^f��ʉ߂���̂̐����J�E���g���C���̒l��Ԃ��D
	 * @param f �W�c�̒��������̏����𖞂����v�f������I�яo���t�B���^
	 * @return �t�B���^f��true��Ԃ��v�f�̐�
	 * @since 98
	 */
	public int frequency(IPopulationFilter f) {
		int freq = 0;
		for (int i = 0; i < fPopulation.getSize(); i++) {
			IIndividual ind = fPopulation.getIndividual(i);
			if (f.accept(i, ind)) {
				freq++;
			}
		}
		return freq;
	}

	/**
	 * �W�c���̌̂����̏����Ńt�B���^�����O���C�����𖞂������݂̂̂��琬��V���ȏW�c��Ԃ��D
	 * @param f �t�B���^
	 * @return �t�B���^�����O����C�����𖞂����݂̂̂ō\�����ꂽ�W�c
	 * @since 98
	 */
	public TPopulation subset(IPopulationFilter f) {
		ArrayList tmp = new ArrayList(fPopulation.getSize() / 2);
		for (int i = 0; i < fPopulation.getSize(); i++) {
			IIndividual ind = fPopulation.getIndividual(i);
			if (f.accept(i, ind)) {
				tmp.add(ind);
			}
		}
		TPopulation p = new TPopulation(fPopulation.getIndividualFactory(), tmp.size());
		for (int i = 0; i < tmp.size(); i++) {
			p.setIndividual(i, (IIndividual) tmp.get(i));
		}
		return p;
	}
	
	/**
	 * ��a�ƌ�b���r����D
	 * @param a ��A
	 * @param b ��B
	 * @param problem ���
	 * @return a����b���D��Ă���ꍇ��true, �����łȂ��Ƃ���false
	 * @since 2
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
	 * ��a�ƌ�b�̕]���l���������ǂ������ׂ�
	 * @return �����Ftrue, �قȂ�Ffalse
	 * @since 2
	 */
	public boolean isAEqualToB(IIndividual a, IIndividual b) {
		return Math.abs(a.getEvaluationValue() - b.getEvaluationValue()) < EPS;
	}
	
}
