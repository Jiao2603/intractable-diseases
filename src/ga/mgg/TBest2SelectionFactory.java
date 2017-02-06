package ga.mgg;

import ga.mgg.ISelectionForSurvival;
import ga.mgg.ISelectionForSurvivalFactory;
import ga.mgg.TBest2Selection;

/**
 * ��ʂQ�̂�I�����鐶���I����̃t�@�N�g���D
 * @since 2
 * @author isao
 */
public class TBest2SelectionFactory implements ISelectionForSurvivalFactory {
	
	/** �ŏ������H */
	private boolean fMinimization;

	/**
	 * �R���X�g���N�^
	 * @param minimization �ŏ����Ftrue, �ő剻�Ffalse
	 */
	public TBest2SelectionFactory(boolean minimization) {
		fMinimization = minimization;
	}
	
	/**
	 * @see mgg.ISelectionForSurvivalFactory#create()
	 * @since 2
	 * @author isao
	 */
	public ISelectionForSurvival create() {
		return new TBest2Selection(fMinimization);
	}

}
