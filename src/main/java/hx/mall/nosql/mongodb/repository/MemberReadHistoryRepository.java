package hx.mall.nosql.mongodb.repository;

import hx.mall.nosql.mongodb.document.MemberReadHistory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * 会员商品浏览历史 Repository
 */
public interface MemberReadHistoryRepository extends MongoRepository<MemberReadHistory,String > {
    /**
     * 根据会员id按时间倒叙获取浏览记录
     */
    List<MemberReadHistory> findByMemberIdOrderByCreateTimeDesc(Long memberId);
}
