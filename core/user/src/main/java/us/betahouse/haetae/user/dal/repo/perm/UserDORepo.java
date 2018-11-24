/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.dal.repo.perm;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import us.betahouse.haetae.user.dal.model.perm.UserDO;

import java.util.List;


/**
 * 用户实体仓储
 *
 * @author dango.yxm
 * @version : UserRepository.java 2018/11/15 下午1:11 dango.yxm
 */
@Repository
public interface UserDORepo extends JpaRepository<UserDO, Long> {

    /**
     * 通过用户名获取用户实体
     *
     * @param userName
     * @return
     */
    UserDO findByUsername(String userName);

    /**
     * 通过用户id获取
     *
     * @param userId
     * @return
     */
    UserDO findByUserId(String userId);

    /**
     * 通过用户id 检查用户是存在
     *
     * @param userId
     * @return
     */
    boolean existsByUserId(String userId);

    /**
     * 通过openId 查询用户信息
     *
     * @param openId
     * @return
     */
    UserDO findByOpenId(String openId);

    /**
     * 批量获取用户
     *
     * @param userIds
     * @return
     */
    List<UserDO> findAllByUserIdIn(List<String> userIds);

    /**
     * 会话获取用户
     *
     * @param sessionId
     * @return
     */
    UserDO findBySessionId(String sessionId);

}
