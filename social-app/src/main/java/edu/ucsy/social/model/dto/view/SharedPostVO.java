package edu.ucsy.social.model.dto.view;

public class SharedPostVO extends PostVO {

	private OriginalPostVO original;

	public OriginalPostVO getOriginal() {
		return original;
	}

	public void setOriginal(OriginalPostVO original) {
		this.original = original;
	}

}