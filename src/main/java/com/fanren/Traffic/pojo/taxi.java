package com.fanren.Traffic.pojo;

public class taxi {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column taxi.Id
     *
     * @mbg.generated Thu Sep 20 22:16:50 CST 2018
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column taxi.taxi_id
     *
     * @mbg.generated Thu Sep 20 22:16:50 CST 2018
     */
    private String taxiId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column taxi.tel
     *
     * @mbg.generated Thu Sep 20 22:16:50 CST 2018
     */
    private String tel;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column taxi.Id
     *
     * @return the value of taxi.Id
     *
     * @mbg.generated Thu Sep 20 22:16:50 CST 2018
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column taxi.Id
     *
     * @param id the value for taxi.Id
     *
     * @mbg.generated Thu Sep 20 22:16:50 CST 2018
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column taxi.taxi_id
     *
     * @return the value of taxi.taxi_id
     *
     * @mbg.generated Thu Sep 20 22:16:50 CST 2018
     */
    public String getTaxiId() {
        return taxiId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column taxi.taxi_id
     *
     * @param taxiId the value for taxi.taxi_id
     *
     * @mbg.generated Thu Sep 20 22:16:50 CST 2018
     */
    public void setTaxiId(String taxiId) {
        this.taxiId = taxiId == null ? null : taxiId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column taxi.tel
     *
     * @return the value of taxi.tel
     *
     * @mbg.generated Thu Sep 20 22:16:50 CST 2018
     */
    public String getTel() {
        return tel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column taxi.tel
     *
     * @param tel the value for taxi.tel
     *
     * @mbg.generated Thu Sep 20 22:16:50 CST 2018
     */
    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }
}