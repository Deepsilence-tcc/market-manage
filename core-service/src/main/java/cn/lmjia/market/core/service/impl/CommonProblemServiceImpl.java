package cn.lmjia.market.core.service.impl;

import cn.lmjia.market.core.entity.help.CommonProblem;
import cn.lmjia.market.core.entity.help.CommonProblem_;
import cn.lmjia.market.core.repository.help.CommonProblemRepository;
import cn.lmjia.market.core.service.help.CommonProblemService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CommonProblemServiceImpl implements CommonProblemService {

    @Autowired
    private CommonProblemRepository commonProblemRepository;

    @Override
    @Transactional
    public CommonProblem getOne(long id) {
        return commonProblemRepository.getOne(id);
    }

    @Override
    @Transactional
    public CommonProblem addCommonProblem(Long id, String title, String content) {
        CommonProblem commonProblem = null;
        if (id != null) {
            commonProblem = commonProblemRepository.getOne(id);
            commonProblem.setUpdateTime(LocalDateTime.now());
        } else {
            commonProblem = new CommonProblem();
            commonProblem.setCreateTime(LocalDateTime.now());
        }
        commonProblem.setTitle(title);
        commonProblem.setContent(content);
        commonProblem.setEnable(true);
        commonProblem.setWeight(false);
        commonProblemRepository.save(commonProblem);
        return commonProblem;
    }

    @Override
    @Transactional
    public List<CommonProblem> findByIsWeigth() {
        List<CommonProblem> result = commonProblemRepository.findAll((root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList<>();
            predicateList.add(cb.isTrue(root.get(CommonProblem_.enable)));
            predicateList.add(cb.isTrue(root.get(CommonProblem_.isWeight)));
            return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
        });
        return result;
    }

    @Override
    @Transactional
    public List<CommonProblem> findByTitle(String keyword) {
        List<CommonProblem> result = commonProblemRepository.findAll((root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList<>();
            predicateList.add(cb.isTrue(root.get(CommonProblem_.enable)));
            if (StringUtils.isNotEmpty(keyword)) {
                predicateList.add(cb.or(cb.like(root.get(CommonProblem_.content), "%" + keyword + "%"), cb.like(root.get(CommonProblem_.title), "%" + keyword + "%")));
            }
            return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
        });
        return result;
    }


}