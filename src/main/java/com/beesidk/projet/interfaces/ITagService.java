package com.beesidk.projet.interfaces;

import com.beesidk.projet.entity.Cour;
import com.beesidk.projet.entity.Tag;

import java.util.List;

public interface ITagService extends IService<Tag> {

    List<Tag> retrieveByTag(String tag);
}
