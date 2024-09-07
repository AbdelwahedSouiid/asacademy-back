package com.beesidk.projet.interfaces;

import com.beesidk.projet.entity.Video;


import java.util.List;

public interface IVideoService extends IService<Video> {

    List<Video> search(String titre, String category);
}
