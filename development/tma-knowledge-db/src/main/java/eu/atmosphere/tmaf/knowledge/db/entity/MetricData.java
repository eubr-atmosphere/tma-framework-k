/**
 * <b>ATMOSPHERE</b> - http://www.atmosphere-eubrazil.eu/
 * ***
 * <p>
 * <b>Trustworthiness Monitoring & Assessment Framework</b>
 * Component: Knowledge - DB
 * <p>
 * Repository: https://github.com/eubr-atmosphere/tma-framework
 * License: https://github.com/eubr-atmosphere/tma-framework/blob/master/LICENSE
 * <p>
 * <p>
 */
package eu.atmosphere.tmaf.knowledge.db.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * 
 * 
 * @author Jorge Luiz <jorgem@unicamp.br>
 * @author Breno de França <breno@ic.unicamp.br>
 * @author José Pereira <josep@dei.uc.pt>
 * @author Nuno Antunes <nmsa@dei.uc.pt>
 */
@Entity
@Table(name = "MetricData")
@NamedQueries({
    @NamedQuery(name = "MetricData.findAll", query = "SELECT m FROM MetricData m"),
    @NamedQuery(name = "MetricData.findByMetricId", query = "SELECT m FROM MetricData m WHERE m.metricDataPK.metricId = :metricId"),
    @NamedQuery(name = "MetricData.findByValueTime", query = "SELECT m FROM MetricData m WHERE m.metricDataPK.valueTime = :valueTime"),
    @NamedQuery(name = "MetricData.findByValue", query = "SELECT m FROM MetricData m WHERE m.value = :value")})
public class MetricData implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MetricDataPK metricDataPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "value")
    private Double value;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "metricData")
    private Collection<Plan> planCollection;
    @JoinColumn(name = "metricId", referencedColumnName = "metricId", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Metric metric;
    @JoinColumn(name = "resourceId", referencedColumnName = "resourceId")
    @ManyToOne
    private Resource resourceId;

    public MetricData() {
    }

    public MetricData(MetricDataPK metricDataPK) {
        this.metricDataPK = metricDataPK;
    }

    public MetricData(int metricId, Date valueTime) {
        this.metricDataPK = new MetricDataPK(metricId, valueTime);
    }

    public MetricDataPK getMetricDataPK() {
        return metricDataPK;
    }

    public void setMetricDataPK(MetricDataPK metricDataPK) {
        this.metricDataPK = metricDataPK;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Collection<Plan> getPlanCollection() {
        return planCollection;
    }

    public void setPlanCollection(Collection<Plan> planCollection) {
        this.planCollection = planCollection;
    }

    public Metric getMetric() {
        return metric;
    }

    public void setMetric(Metric metric) {
        this.metric = metric;
    }

    public Resource getResourceId() {
        return resourceId;
    }

    public void setResourceId(Resource resourceId) {
        this.resourceId = resourceId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (metricDataPK != null ? metricDataPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MetricData)) {
            return false;
        }
        MetricData other = (MetricData) object;
        if ((this.metricDataPK == null && other.metricDataPK != null) || (this.metricDataPK != null && !this.metricDataPK.equals(other.metricDataPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eu.atmosphere.tmaf.knowledge.db.entity.MetricData[ metricDataPK=" + metricDataPK + " ]";
    }
    
}
