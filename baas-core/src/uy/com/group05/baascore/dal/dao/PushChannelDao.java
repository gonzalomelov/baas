package uy.com.group05.baascore.dal.dao;

import java.util.List;

import uy.com.group05.baascore.common.entities.Client;
import uy.com.group05.baascore.common.entities.PushChannel;

public interface PushChannelDao extends GenericDao<PushChannel> {
	PushChannel readByName(long appId, String name);
    List<PushChannel> readAllFromApp(long appId);
    List<PushChannel> readAllFromClient(long appId, long clientId);
    List<Client> readAllFromCanal(long appId, long canalId);
}
