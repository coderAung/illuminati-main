package edu.ucsy.social.model.dto.view;

import java.util.List;

public class OriginalPostVO extends PostVO {

    private List<String> images;

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

}