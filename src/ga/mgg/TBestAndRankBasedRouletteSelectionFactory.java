package ga.mgg;

/**
 * TBestAndRankBasedRouletteSelection�̃t�@�N�g��
 * @since 2
 * @author isao
 */
public class TBestAndRankBasedRouletteSelectionFactory implements ISelectionForSurvivalFactory {
	
	/** �ŏ������H */
	private boolean fMinimization;

	public TBestAndRankBasedRouletteSelectionFactory(boolean isMinimization) {
		fMinimization = isMinimization;
	}
	
	/**
	 * @see ISelectionForSurvivalFactory#create()
	 * @since 2
	 * @author isao
	 */
	public ISelectionForSurvival create() {
		return new TBestAndRankBasedRouletteSelection(fMinimization);
	}

}
