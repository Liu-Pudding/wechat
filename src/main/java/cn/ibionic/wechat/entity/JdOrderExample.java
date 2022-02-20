package cn.ibionic.wechat.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JdOrderExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public JdOrderExample() {
        oredCriteria = new ArrayList<>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNull() {
            addCriterion("order_id is null");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNotNull() {
            addCriterion("order_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrderIdEqualTo(Long value) {
            addCriterion("order_id =", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotEqualTo(Long value) {
            addCriterion("order_id <>", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThan(Long value) {
            addCriterion("order_id >", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThanOrEqualTo(Long value) {
            addCriterion("order_id >=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThan(Long value) {
            addCriterion("order_id <", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThanOrEqualTo(Long value) {
            addCriterion("order_id <=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdIn(List<Long> values) {
            addCriterion("order_id in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotIn(List<Long> values) {
            addCriterion("order_id not in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdBetween(Long value1, Long value2) {
            addCriterion("order_id between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotBetween(Long value1, Long value2) {
            addCriterion("order_id not between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNull() {
            addCriterion("parent_id is null");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNotNull() {
            addCriterion("parent_id is not null");
            return (Criteria) this;
        }

        public Criteria andParentIdEqualTo(Long value) {
            addCriterion("parent_id =", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotEqualTo(Long value) {
            addCriterion("parent_id <>", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThan(Long value) {
            addCriterion("parent_id >", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThanOrEqualTo(Long value) {
            addCriterion("parent_id >=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThan(Long value) {
            addCriterion("parent_id <", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThanOrEqualTo(Long value) {
            addCriterion("parent_id <=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdIn(List<Long> values) {
            addCriterion("parent_id in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotIn(List<Long> values) {
            addCriterion("parent_id not in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdBetween(Long value1, Long value2) {
            addCriterion("parent_id between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotBetween(Long value1, Long value2) {
            addCriterion("parent_id not between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andOrderTimeIsNull() {
            addCriterion("order_time is null");
            return (Criteria) this;
        }

        public Criteria andOrderTimeIsNotNull() {
            addCriterion("order_time is not null");
            return (Criteria) this;
        }

        public Criteria andOrderTimeEqualTo(Date value) {
            addCriterion("order_time =", value, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeNotEqualTo(Date value) {
            addCriterion("order_time <>", value, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeGreaterThan(Date value) {
            addCriterion("order_time >", value, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("order_time >=", value, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeLessThan(Date value) {
            addCriterion("order_time <", value, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeLessThanOrEqualTo(Date value) {
            addCriterion("order_time <=", value, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeIn(List<Date> values) {
            addCriterion("order_time in", values, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeNotIn(List<Date> values) {
            addCriterion("order_time not in", values, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeBetween(Date value1, Date value2) {
            addCriterion("order_time between", value1, value2, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeNotBetween(Date value1, Date value2) {
            addCriterion("order_time not between", value1, value2, "orderTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeIsNull() {
            addCriterion("finish_time is null");
            return (Criteria) this;
        }

        public Criteria andFinishTimeIsNotNull() {
            addCriterion("finish_time is not null");
            return (Criteria) this;
        }

        public Criteria andFinishTimeEqualTo(Date value) {
            addCriterion("finish_time =", value, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeNotEqualTo(Date value) {
            addCriterion("finish_time <>", value, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeGreaterThan(Date value) {
            addCriterion("finish_time >", value, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("finish_time >=", value, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeLessThan(Date value) {
            addCriterion("finish_time <", value, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeLessThanOrEqualTo(Date value) {
            addCriterion("finish_time <=", value, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeIn(List<Date> values) {
            addCriterion("finish_time in", values, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeNotIn(List<Date> values) {
            addCriterion("finish_time not in", values, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeBetween(Date value1, Date value2) {
            addCriterion("finish_time between", value1, value2, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeNotBetween(Date value1, Date value2) {
            addCriterion("finish_time not between", value1, value2, "finishTime");
            return (Criteria) this;
        }

        public Criteria andPlusIsNull() {
            addCriterion("plus is null");
            return (Criteria) this;
        }

        public Criteria andPlusIsNotNull() {
            addCriterion("plus is not null");
            return (Criteria) this;
        }

        public Criteria andPlusEqualTo(Boolean value) {
            addCriterion("plus =", value, "plus");
            return (Criteria) this;
        }

        public Criteria andPlusNotEqualTo(Boolean value) {
            addCriterion("plus <>", value, "plus");
            return (Criteria) this;
        }

        public Criteria andPlusGreaterThan(Boolean value) {
            addCriterion("plus >", value, "plus");
            return (Criteria) this;
        }

        public Criteria andPlusGreaterThanOrEqualTo(Boolean value) {
            addCriterion("plus >=", value, "plus");
            return (Criteria) this;
        }

        public Criteria andPlusLessThan(Boolean value) {
            addCriterion("plus <", value, "plus");
            return (Criteria) this;
        }

        public Criteria andPlusLessThanOrEqualTo(Boolean value) {
            addCriterion("plus <=", value, "plus");
            return (Criteria) this;
        }

        public Criteria andPlusIn(List<Boolean> values) {
            addCriterion("plus in", values, "plus");
            return (Criteria) this;
        }

        public Criteria andPlusNotIn(List<Boolean> values) {
            addCriterion("plus not in", values, "plus");
            return (Criteria) this;
        }

        public Criteria andPlusBetween(Boolean value1, Boolean value2) {
            addCriterion("plus between", value1, value2, "plus");
            return (Criteria) this;
        }

        public Criteria andPlusNotBetween(Boolean value1, Boolean value2) {
            addCriterion("plus not between", value1, value2, "plus");
            return (Criteria) this;
        }

        public Criteria andSkuIdIsNull() {
            addCriterion("sku_id is null");
            return (Criteria) this;
        }

        public Criteria andSkuIdIsNotNull() {
            addCriterion("sku_id is not null");
            return (Criteria) this;
        }

        public Criteria andSkuIdEqualTo(Long value) {
            addCriterion("sku_id =", value, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdNotEqualTo(Long value) {
            addCriterion("sku_id <>", value, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdGreaterThan(Long value) {
            addCriterion("sku_id >", value, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdGreaterThanOrEqualTo(Long value) {
            addCriterion("sku_id >=", value, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdLessThan(Long value) {
            addCriterion("sku_id <", value, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdLessThanOrEqualTo(Long value) {
            addCriterion("sku_id <=", value, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdIn(List<Long> values) {
            addCriterion("sku_id in", values, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdNotIn(List<Long> values) {
            addCriterion("sku_id not in", values, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdBetween(Long value1, Long value2) {
            addCriterion("sku_id between", value1, value2, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdNotBetween(Long value1, Long value2) {
            addCriterion("sku_id not between", value1, value2, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuNameIsNull() {
            addCriterion("sku_name is null");
            return (Criteria) this;
        }

        public Criteria andSkuNameIsNotNull() {
            addCriterion("sku_name is not null");
            return (Criteria) this;
        }

        public Criteria andSkuNameEqualTo(String value) {
            addCriterion("sku_name =", value, "skuName");
            return (Criteria) this;
        }

        public Criteria andSkuNameNotEqualTo(String value) {
            addCriterion("sku_name <>", value, "skuName");
            return (Criteria) this;
        }

        public Criteria andSkuNameGreaterThan(String value) {
            addCriterion("sku_name >", value, "skuName");
            return (Criteria) this;
        }

        public Criteria andSkuNameGreaterThanOrEqualTo(String value) {
            addCriterion("sku_name >=", value, "skuName");
            return (Criteria) this;
        }

        public Criteria andSkuNameLessThan(String value) {
            addCriterion("sku_name <", value, "skuName");
            return (Criteria) this;
        }

        public Criteria andSkuNameLessThanOrEqualTo(String value) {
            addCriterion("sku_name <=", value, "skuName");
            return (Criteria) this;
        }

        public Criteria andSkuNameLike(String value) {
            addCriterion("sku_name like", value, "skuName");
            return (Criteria) this;
        }

        public Criteria andSkuNameNotLike(String value) {
            addCriterion("sku_name not like", value, "skuName");
            return (Criteria) this;
        }

        public Criteria andSkuNameIn(List<String> values) {
            addCriterion("sku_name in", values, "skuName");
            return (Criteria) this;
        }

        public Criteria andSkuNameNotIn(List<String> values) {
            addCriterion("sku_name not in", values, "skuName");
            return (Criteria) this;
        }

        public Criteria andSkuNameBetween(String value1, String value2) {
            addCriterion("sku_name between", value1, value2, "skuName");
            return (Criteria) this;
        }

        public Criteria andSkuNameNotBetween(String value1, String value2) {
            addCriterion("sku_name not between", value1, value2, "skuName");
            return (Criteria) this;
        }

        public Criteria andSkuNumIsNull() {
            addCriterion("sku_num is null");
            return (Criteria) this;
        }

        public Criteria andSkuNumIsNotNull() {
            addCriterion("sku_num is not null");
            return (Criteria) this;
        }

        public Criteria andSkuNumEqualTo(Integer value) {
            addCriterion("sku_num =", value, "skuNum");
            return (Criteria) this;
        }

        public Criteria andSkuNumNotEqualTo(Integer value) {
            addCriterion("sku_num <>", value, "skuNum");
            return (Criteria) this;
        }

        public Criteria andSkuNumGreaterThan(Integer value) {
            addCriterion("sku_num >", value, "skuNum");
            return (Criteria) this;
        }

        public Criteria andSkuNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("sku_num >=", value, "skuNum");
            return (Criteria) this;
        }

        public Criteria andSkuNumLessThan(Integer value) {
            addCriterion("sku_num <", value, "skuNum");
            return (Criteria) this;
        }

        public Criteria andSkuNumLessThanOrEqualTo(Integer value) {
            addCriterion("sku_num <=", value, "skuNum");
            return (Criteria) this;
        }

        public Criteria andSkuNumIn(List<Integer> values) {
            addCriterion("sku_num in", values, "skuNum");
            return (Criteria) this;
        }

        public Criteria andSkuNumNotIn(List<Integer> values) {
            addCriterion("sku_num not in", values, "skuNum");
            return (Criteria) this;
        }

        public Criteria andSkuNumBetween(Integer value1, Integer value2) {
            addCriterion("sku_num between", value1, value2, "skuNum");
            return (Criteria) this;
        }

        public Criteria andSkuNumNotBetween(Integer value1, Integer value2) {
            addCriterion("sku_num not between", value1, value2, "skuNum");
            return (Criteria) this;
        }

        public Criteria andPriceIsNull() {
            addCriterion("price is null");
            return (Criteria) this;
        }

        public Criteria andPriceIsNotNull() {
            addCriterion("price is not null");
            return (Criteria) this;
        }

        public Criteria andPriceEqualTo(Double value) {
            addCriterion("price =", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotEqualTo(Double value) {
            addCriterion("price <>", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThan(Double value) {
            addCriterion("price >", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThanOrEqualTo(Double value) {
            addCriterion("price >=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThan(Double value) {
            addCriterion("price <", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThanOrEqualTo(Double value) {
            addCriterion("price <=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceIn(List<Double> values) {
            addCriterion("price in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotIn(List<Double> values) {
            addCriterion("price not in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceBetween(Double value1, Double value2) {
            addCriterion("price between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotBetween(Double value1, Double value2) {
            addCriterion("price not between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andCommissionRateIsNull() {
            addCriterion("commission_rate is null");
            return (Criteria) this;
        }

        public Criteria andCommissionRateIsNotNull() {
            addCriterion("commission_rate is not null");
            return (Criteria) this;
        }

        public Criteria andCommissionRateEqualTo(Double value) {
            addCriterion("commission_rate =", value, "commissionRate");
            return (Criteria) this;
        }

        public Criteria andCommissionRateNotEqualTo(Double value) {
            addCriterion("commission_rate <>", value, "commissionRate");
            return (Criteria) this;
        }

        public Criteria andCommissionRateGreaterThan(Double value) {
            addCriterion("commission_rate >", value, "commissionRate");
            return (Criteria) this;
        }

        public Criteria andCommissionRateGreaterThanOrEqualTo(Double value) {
            addCriterion("commission_rate >=", value, "commissionRate");
            return (Criteria) this;
        }

        public Criteria andCommissionRateLessThan(Double value) {
            addCriterion("commission_rate <", value, "commissionRate");
            return (Criteria) this;
        }

        public Criteria andCommissionRateLessThanOrEqualTo(Double value) {
            addCriterion("commission_rate <=", value, "commissionRate");
            return (Criteria) this;
        }

        public Criteria andCommissionRateIn(List<Double> values) {
            addCriterion("commission_rate in", values, "commissionRate");
            return (Criteria) this;
        }

        public Criteria andCommissionRateNotIn(List<Double> values) {
            addCriterion("commission_rate not in", values, "commissionRate");
            return (Criteria) this;
        }

        public Criteria andCommissionRateBetween(Double value1, Double value2) {
            addCriterion("commission_rate between", value1, value2, "commissionRate");
            return (Criteria) this;
        }

        public Criteria andCommissionRateNotBetween(Double value1, Double value2) {
            addCriterion("commission_rate not between", value1, value2, "commissionRate");
            return (Criteria) this;
        }

        public Criteria andSubSideRateIsNull() {
            addCriterion("sub_side_rate is null");
            return (Criteria) this;
        }

        public Criteria andSubSideRateIsNotNull() {
            addCriterion("sub_side_rate is not null");
            return (Criteria) this;
        }

        public Criteria andSubSideRateEqualTo(Double value) {
            addCriterion("sub_side_rate =", value, "subSideRate");
            return (Criteria) this;
        }

        public Criteria andSubSideRateNotEqualTo(Double value) {
            addCriterion("sub_side_rate <>", value, "subSideRate");
            return (Criteria) this;
        }

        public Criteria andSubSideRateGreaterThan(Double value) {
            addCriterion("sub_side_rate >", value, "subSideRate");
            return (Criteria) this;
        }

        public Criteria andSubSideRateGreaterThanOrEqualTo(Double value) {
            addCriterion("sub_side_rate >=", value, "subSideRate");
            return (Criteria) this;
        }

        public Criteria andSubSideRateLessThan(Double value) {
            addCriterion("sub_side_rate <", value, "subSideRate");
            return (Criteria) this;
        }

        public Criteria andSubSideRateLessThanOrEqualTo(Double value) {
            addCriterion("sub_side_rate <=", value, "subSideRate");
            return (Criteria) this;
        }

        public Criteria andSubSideRateIn(List<Double> values) {
            addCriterion("sub_side_rate in", values, "subSideRate");
            return (Criteria) this;
        }

        public Criteria andSubSideRateNotIn(List<Double> values) {
            addCriterion("sub_side_rate not in", values, "subSideRate");
            return (Criteria) this;
        }

        public Criteria andSubSideRateBetween(Double value1, Double value2) {
            addCriterion("sub_side_rate between", value1, value2, "subSideRate");
            return (Criteria) this;
        }

        public Criteria andSubSideRateNotBetween(Double value1, Double value2) {
            addCriterion("sub_side_rate not between", value1, value2, "subSideRate");
            return (Criteria) this;
        }

        public Criteria andSubsidyRateIsNull() {
            addCriterion("subsidy_rate is null");
            return (Criteria) this;
        }

        public Criteria andSubsidyRateIsNotNull() {
            addCriterion("subsidy_rate is not null");
            return (Criteria) this;
        }

        public Criteria andSubsidyRateEqualTo(Double value) {
            addCriterion("subsidy_rate =", value, "subsidyRate");
            return (Criteria) this;
        }

        public Criteria andSubsidyRateNotEqualTo(Double value) {
            addCriterion("subsidy_rate <>", value, "subsidyRate");
            return (Criteria) this;
        }

        public Criteria andSubsidyRateGreaterThan(Double value) {
            addCriterion("subsidy_rate >", value, "subsidyRate");
            return (Criteria) this;
        }

        public Criteria andSubsidyRateGreaterThanOrEqualTo(Double value) {
            addCriterion("subsidy_rate >=", value, "subsidyRate");
            return (Criteria) this;
        }

        public Criteria andSubsidyRateLessThan(Double value) {
            addCriterion("subsidy_rate <", value, "subsidyRate");
            return (Criteria) this;
        }

        public Criteria andSubsidyRateLessThanOrEqualTo(Double value) {
            addCriterion("subsidy_rate <=", value, "subsidyRate");
            return (Criteria) this;
        }

        public Criteria andSubsidyRateIn(List<Double> values) {
            addCriterion("subsidy_rate in", values, "subsidyRate");
            return (Criteria) this;
        }

        public Criteria andSubsidyRateNotIn(List<Double> values) {
            addCriterion("subsidy_rate not in", values, "subsidyRate");
            return (Criteria) this;
        }

        public Criteria andSubsidyRateBetween(Double value1, Double value2) {
            addCriterion("subsidy_rate between", value1, value2, "subsidyRate");
            return (Criteria) this;
        }

        public Criteria andSubsidyRateNotBetween(Double value1, Double value2) {
            addCriterion("subsidy_rate not between", value1, value2, "subsidyRate");
            return (Criteria) this;
        }

        public Criteria andFinalRateIsNull() {
            addCriterion("final_rate is null");
            return (Criteria) this;
        }

        public Criteria andFinalRateIsNotNull() {
            addCriterion("final_rate is not null");
            return (Criteria) this;
        }

        public Criteria andFinalRateEqualTo(Double value) {
            addCriterion("final_rate =", value, "finalRate");
            return (Criteria) this;
        }

        public Criteria andFinalRateNotEqualTo(Double value) {
            addCriterion("final_rate <>", value, "finalRate");
            return (Criteria) this;
        }

        public Criteria andFinalRateGreaterThan(Double value) {
            addCriterion("final_rate >", value, "finalRate");
            return (Criteria) this;
        }

        public Criteria andFinalRateGreaterThanOrEqualTo(Double value) {
            addCriterion("final_rate >=", value, "finalRate");
            return (Criteria) this;
        }

        public Criteria andFinalRateLessThan(Double value) {
            addCriterion("final_rate <", value, "finalRate");
            return (Criteria) this;
        }

        public Criteria andFinalRateLessThanOrEqualTo(Double value) {
            addCriterion("final_rate <=", value, "finalRate");
            return (Criteria) this;
        }

        public Criteria andFinalRateIn(List<Double> values) {
            addCriterion("final_rate in", values, "finalRate");
            return (Criteria) this;
        }

        public Criteria andFinalRateNotIn(List<Double> values) {
            addCriterion("final_rate not in", values, "finalRate");
            return (Criteria) this;
        }

        public Criteria andFinalRateBetween(Double value1, Double value2) {
            addCriterion("final_rate between", value1, value2, "finalRate");
            return (Criteria) this;
        }

        public Criteria andFinalRateNotBetween(Double value1, Double value2) {
            addCriterion("final_rate not between", value1, value2, "finalRate");
            return (Criteria) this;
        }

        public Criteria andEstimateCosPriceIsNull() {
            addCriterion("estimate_cos_price is null");
            return (Criteria) this;
        }

        public Criteria andEstimateCosPriceIsNotNull() {
            addCriterion("estimate_cos_price is not null");
            return (Criteria) this;
        }

        public Criteria andEstimateCosPriceEqualTo(Double value) {
            addCriterion("estimate_cos_price =", value, "estimateCosPrice");
            return (Criteria) this;
        }

        public Criteria andEstimateCosPriceNotEqualTo(Double value) {
            addCriterion("estimate_cos_price <>", value, "estimateCosPrice");
            return (Criteria) this;
        }

        public Criteria andEstimateCosPriceGreaterThan(Double value) {
            addCriterion("estimate_cos_price >", value, "estimateCosPrice");
            return (Criteria) this;
        }

        public Criteria andEstimateCosPriceGreaterThanOrEqualTo(Double value) {
            addCriterion("estimate_cos_price >=", value, "estimateCosPrice");
            return (Criteria) this;
        }

        public Criteria andEstimateCosPriceLessThan(Double value) {
            addCriterion("estimate_cos_price <", value, "estimateCosPrice");
            return (Criteria) this;
        }

        public Criteria andEstimateCosPriceLessThanOrEqualTo(Double value) {
            addCriterion("estimate_cos_price <=", value, "estimateCosPrice");
            return (Criteria) this;
        }

        public Criteria andEstimateCosPriceIn(List<Double> values) {
            addCriterion("estimate_cos_price in", values, "estimateCosPrice");
            return (Criteria) this;
        }

        public Criteria andEstimateCosPriceNotIn(List<Double> values) {
            addCriterion("estimate_cos_price not in", values, "estimateCosPrice");
            return (Criteria) this;
        }

        public Criteria andEstimateCosPriceBetween(Double value1, Double value2) {
            addCriterion("estimate_cos_price between", value1, value2, "estimateCosPrice");
            return (Criteria) this;
        }

        public Criteria andEstimateCosPriceNotBetween(Double value1, Double value2) {
            addCriterion("estimate_cos_price not between", value1, value2, "estimateCosPrice");
            return (Criteria) this;
        }

        public Criteria andEstimateFeeIsNull() {
            addCriterion("estimate_fee is null");
            return (Criteria) this;
        }

        public Criteria andEstimateFeeIsNotNull() {
            addCriterion("estimate_fee is not null");
            return (Criteria) this;
        }

        public Criteria andEstimateFeeEqualTo(Double value) {
            addCriterion("estimate_fee =", value, "estimateFee");
            return (Criteria) this;
        }

        public Criteria andEstimateFeeNotEqualTo(Double value) {
            addCriterion("estimate_fee <>", value, "estimateFee");
            return (Criteria) this;
        }

        public Criteria andEstimateFeeGreaterThan(Double value) {
            addCriterion("estimate_fee >", value, "estimateFee");
            return (Criteria) this;
        }

        public Criteria andEstimateFeeGreaterThanOrEqualTo(Double value) {
            addCriterion("estimate_fee >=", value, "estimateFee");
            return (Criteria) this;
        }

        public Criteria andEstimateFeeLessThan(Double value) {
            addCriterion("estimate_fee <", value, "estimateFee");
            return (Criteria) this;
        }

        public Criteria andEstimateFeeLessThanOrEqualTo(Double value) {
            addCriterion("estimate_fee <=", value, "estimateFee");
            return (Criteria) this;
        }

        public Criteria andEstimateFeeIn(List<Double> values) {
            addCriterion("estimate_fee in", values, "estimateFee");
            return (Criteria) this;
        }

        public Criteria andEstimateFeeNotIn(List<Double> values) {
            addCriterion("estimate_fee not in", values, "estimateFee");
            return (Criteria) this;
        }

        public Criteria andEstimateFeeBetween(Double value1, Double value2) {
            addCriterion("estimate_fee between", value1, value2, "estimateFee");
            return (Criteria) this;
        }

        public Criteria andEstimateFeeNotBetween(Double value1, Double value2) {
            addCriterion("estimate_fee not between", value1, value2, "estimateFee");
            return (Criteria) this;
        }

        public Criteria andActualCosPriceIsNull() {
            addCriterion("actual_cos_price is null");
            return (Criteria) this;
        }

        public Criteria andActualCosPriceIsNotNull() {
            addCriterion("actual_cos_price is not null");
            return (Criteria) this;
        }

        public Criteria andActualCosPriceEqualTo(Double value) {
            addCriterion("actual_cos_price =", value, "actualCosPrice");
            return (Criteria) this;
        }

        public Criteria andActualCosPriceNotEqualTo(Double value) {
            addCriterion("actual_cos_price <>", value, "actualCosPrice");
            return (Criteria) this;
        }

        public Criteria andActualCosPriceGreaterThan(Double value) {
            addCriterion("actual_cos_price >", value, "actualCosPrice");
            return (Criteria) this;
        }

        public Criteria andActualCosPriceGreaterThanOrEqualTo(Double value) {
            addCriterion("actual_cos_price >=", value, "actualCosPrice");
            return (Criteria) this;
        }

        public Criteria andActualCosPriceLessThan(Double value) {
            addCriterion("actual_cos_price <", value, "actualCosPrice");
            return (Criteria) this;
        }

        public Criteria andActualCosPriceLessThanOrEqualTo(Double value) {
            addCriterion("actual_cos_price <=", value, "actualCosPrice");
            return (Criteria) this;
        }

        public Criteria andActualCosPriceIn(List<Double> values) {
            addCriterion("actual_cos_price in", values, "actualCosPrice");
            return (Criteria) this;
        }

        public Criteria andActualCosPriceNotIn(List<Double> values) {
            addCriterion("actual_cos_price not in", values, "actualCosPrice");
            return (Criteria) this;
        }

        public Criteria andActualCosPriceBetween(Double value1, Double value2) {
            addCriterion("actual_cos_price between", value1, value2, "actualCosPrice");
            return (Criteria) this;
        }

        public Criteria andActualCosPriceNotBetween(Double value1, Double value2) {
            addCriterion("actual_cos_price not between", value1, value2, "actualCosPrice");
            return (Criteria) this;
        }

        public Criteria andActualFeeIsNull() {
            addCriterion("actual_fee is null");
            return (Criteria) this;
        }

        public Criteria andActualFeeIsNotNull() {
            addCriterion("actual_fee is not null");
            return (Criteria) this;
        }

        public Criteria andActualFeeEqualTo(Double value) {
            addCriterion("actual_fee =", value, "actualFee");
            return (Criteria) this;
        }

        public Criteria andActualFeeNotEqualTo(Double value) {
            addCriterion("actual_fee <>", value, "actualFee");
            return (Criteria) this;
        }

        public Criteria andActualFeeGreaterThan(Double value) {
            addCriterion("actual_fee >", value, "actualFee");
            return (Criteria) this;
        }

        public Criteria andActualFeeGreaterThanOrEqualTo(Double value) {
            addCriterion("actual_fee >=", value, "actualFee");
            return (Criteria) this;
        }

        public Criteria andActualFeeLessThan(Double value) {
            addCriterion("actual_fee <", value, "actualFee");
            return (Criteria) this;
        }

        public Criteria andActualFeeLessThanOrEqualTo(Double value) {
            addCriterion("actual_fee <=", value, "actualFee");
            return (Criteria) this;
        }

        public Criteria andActualFeeIn(List<Double> values) {
            addCriterion("actual_fee in", values, "actualFee");
            return (Criteria) this;
        }

        public Criteria andActualFeeNotIn(List<Double> values) {
            addCriterion("actual_fee not in", values, "actualFee");
            return (Criteria) this;
        }

        public Criteria andActualFeeBetween(Double value1, Double value2) {
            addCriterion("actual_fee between", value1, value2, "actualFee");
            return (Criteria) this;
        }

        public Criteria andActualFeeNotBetween(Double value1, Double value2) {
            addCriterion("actual_fee not between", value1, value2, "actualFee");
            return (Criteria) this;
        }

        public Criteria andPayMonthIsNull() {
            addCriterion("pay_month is null");
            return (Criteria) this;
        }

        public Criteria andPayMonthIsNotNull() {
            addCriterion("pay_month is not null");
            return (Criteria) this;
        }

        public Criteria andPayMonthEqualTo(String value) {
            addCriterion("pay_month =", value, "payMonth");
            return (Criteria) this;
        }

        public Criteria andPayMonthNotEqualTo(String value) {
            addCriterion("pay_month <>", value, "payMonth");
            return (Criteria) this;
        }

        public Criteria andPayMonthGreaterThan(String value) {
            addCriterion("pay_month >", value, "payMonth");
            return (Criteria) this;
        }

        public Criteria andPayMonthGreaterThanOrEqualTo(String value) {
            addCriterion("pay_month >=", value, "payMonth");
            return (Criteria) this;
        }

        public Criteria andPayMonthLessThan(String value) {
            addCriterion("pay_month <", value, "payMonth");
            return (Criteria) this;
        }

        public Criteria andPayMonthLessThanOrEqualTo(String value) {
            addCriterion("pay_month <=", value, "payMonth");
            return (Criteria) this;
        }

        public Criteria andPayMonthLike(String value) {
            addCriterion("pay_month like", value, "payMonth");
            return (Criteria) this;
        }

        public Criteria andPayMonthNotLike(String value) {
            addCriterion("pay_month not like", value, "payMonth");
            return (Criteria) this;
        }

        public Criteria andPayMonthIn(List<String> values) {
            addCriterion("pay_month in", values, "payMonth");
            return (Criteria) this;
        }

        public Criteria andPayMonthNotIn(List<String> values) {
            addCriterion("pay_month not in", values, "payMonth");
            return (Criteria) this;
        }

        public Criteria andPayMonthBetween(String value1, String value2) {
            addCriterion("pay_month between", value1, value2, "payMonth");
            return (Criteria) this;
        }

        public Criteria andPayMonthNotBetween(String value1, String value2) {
            addCriterion("pay_month not between", value1, value2, "payMonth");
            return (Criteria) this;
        }

        public Criteria andPositionIdIsNull() {
            addCriterion("position_id is null");
            return (Criteria) this;
        }

        public Criteria andPositionIdIsNotNull() {
            addCriterion("position_id is not null");
            return (Criteria) this;
        }

        public Criteria andPositionIdEqualTo(Integer value) {
            addCriterion("position_id =", value, "positionId");
            return (Criteria) this;
        }

        public Criteria andPositionIdNotEqualTo(Integer value) {
            addCriterion("position_id <>", value, "positionId");
            return (Criteria) this;
        }

        public Criteria andPositionIdGreaterThan(Integer value) {
            addCriterion("position_id >", value, "positionId");
            return (Criteria) this;
        }

        public Criteria andPositionIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("position_id >=", value, "positionId");
            return (Criteria) this;
        }

        public Criteria andPositionIdLessThan(Integer value) {
            addCriterion("position_id <", value, "positionId");
            return (Criteria) this;
        }

        public Criteria andPositionIdLessThanOrEqualTo(Integer value) {
            addCriterion("position_id <=", value, "positionId");
            return (Criteria) this;
        }

        public Criteria andPositionIdIn(List<Integer> values) {
            addCriterion("position_id in", values, "positionId");
            return (Criteria) this;
        }

        public Criteria andPositionIdNotIn(List<Integer> values) {
            addCriterion("position_id not in", values, "positionId");
            return (Criteria) this;
        }

        public Criteria andPositionIdBetween(Integer value1, Integer value2) {
            addCriterion("position_id between", value1, value2, "positionId");
            return (Criteria) this;
        }

        public Criteria andPositionIdNotBetween(Integer value1, Integer value2) {
            addCriterion("position_id not between", value1, value2, "positionId");
            return (Criteria) this;
        }

        public Criteria andSubUnionidIsNull() {
            addCriterion("sub_unionId is null");
            return (Criteria) this;
        }

        public Criteria andSubUnionidIsNotNull() {
            addCriterion("sub_unionId is not null");
            return (Criteria) this;
        }

        public Criteria andSubUnionidEqualTo(String value) {
            addCriterion("sub_unionId =", value, "subUnionid");
            return (Criteria) this;
        }

        public Criteria andSubUnionidNotEqualTo(String value) {
            addCriterion("sub_unionId <>", value, "subUnionid");
            return (Criteria) this;
        }

        public Criteria andSubUnionidGreaterThan(String value) {
            addCriterion("sub_unionId >", value, "subUnionid");
            return (Criteria) this;
        }

        public Criteria andSubUnionidGreaterThanOrEqualTo(String value) {
            addCriterion("sub_unionId >=", value, "subUnionid");
            return (Criteria) this;
        }

        public Criteria andSubUnionidLessThan(String value) {
            addCriterion("sub_unionId <", value, "subUnionid");
            return (Criteria) this;
        }

        public Criteria andSubUnionidLessThanOrEqualTo(String value) {
            addCriterion("sub_unionId <=", value, "subUnionid");
            return (Criteria) this;
        }

        public Criteria andSubUnionidLike(String value) {
            addCriterion("sub_unionId like", value, "subUnionid");
            return (Criteria) this;
        }

        public Criteria andSubUnionidNotLike(String value) {
            addCriterion("sub_unionId not like", value, "subUnionid");
            return (Criteria) this;
        }

        public Criteria andSubUnionidIn(List<String> values) {
            addCriterion("sub_unionId in", values, "subUnionid");
            return (Criteria) this;
        }

        public Criteria andSubUnionidNotIn(List<String> values) {
            addCriterion("sub_unionId not in", values, "subUnionid");
            return (Criteria) this;
        }

        public Criteria andSubUnionidBetween(String value1, String value2) {
            addCriterion("sub_unionId between", value1, value2, "subUnionid");
            return (Criteria) this;
        }

        public Criteria andSubUnionidNotBetween(String value1, String value2) {
            addCriterion("sub_unionId not between", value1, value2, "subUnionid");
            return (Criteria) this;
        }

        public Criteria andValidCodeIsNull() {
            addCriterion("valid_code is null");
            return (Criteria) this;
        }

        public Criteria andValidCodeIsNotNull() {
            addCriterion("valid_code is not null");
            return (Criteria) this;
        }

        public Criteria andValidCodeEqualTo(Integer value) {
            addCriterion("valid_code =", value, "validCode");
            return (Criteria) this;
        }

        public Criteria andValidCodeNotEqualTo(Integer value) {
            addCriterion("valid_code <>", value, "validCode");
            return (Criteria) this;
        }

        public Criteria andValidCodeGreaterThan(Integer value) {
            addCriterion("valid_code >", value, "validCode");
            return (Criteria) this;
        }

        public Criteria andValidCodeGreaterThanOrEqualTo(Integer value) {
            addCriterion("valid_code >=", value, "validCode");
            return (Criteria) this;
        }

        public Criteria andValidCodeLessThan(Integer value) {
            addCriterion("valid_code <", value, "validCode");
            return (Criteria) this;
        }

        public Criteria andValidCodeLessThanOrEqualTo(Integer value) {
            addCriterion("valid_code <=", value, "validCode");
            return (Criteria) this;
        }

        public Criteria andValidCodeIn(List<Integer> values) {
            addCriterion("valid_code in", values, "validCode");
            return (Criteria) this;
        }

        public Criteria andValidCodeNotIn(List<Integer> values) {
            addCriterion("valid_code not in", values, "validCode");
            return (Criteria) this;
        }

        public Criteria andValidCodeBetween(Integer value1, Integer value2) {
            addCriterion("valid_code between", value1, value2, "validCode");
            return (Criteria) this;
        }

        public Criteria andValidCodeNotBetween(Integer value1, Integer value2) {
            addCriterion("valid_code not between", value1, value2, "validCode");
            return (Criteria) this;
        }
    }

    /**
     */
    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}