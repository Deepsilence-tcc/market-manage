package cn.lmjia.market.core.entity.support;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author CJ
 */
@Data
@Embeddable
public class Address {
    /**
     * 省(province)/直辖市(municipality)/自治区(autonomous region)/特别行政区(special administrative region/SAR)
     */
    @Column(length = 20)
    private String province;
    /**
     * 地级市(prefecture-level city)/地区(prefecture)/自治州(autonomous prefecture)/盟(league)
     */
    @Column(length = 20)
    private String prefecture;
    /**
     * 县(county)/自治县(autonomous county)/县级市(county-level  city)/市辖区(district)/旗(banner)
     * /自治旗(autonomous banner)/林区(forestry area)/特区(special district)
     */
    @Column(length = 20)
    private String county;
    @Column(length = 100)
    private String otherAddress;

    /**
     * @return 把除了otherAddress之外的地址组织成一个标准格式
     */
    public String getStandardWithoutOther() {
        return province + "/" + prefecture + "/" + county;
    }
}
