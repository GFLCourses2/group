package executor.service.model;

import java.util.List;
import java.util.Objects;

//DTO Scenario
public class Scenario {
    private String name;
    private String site;
    private List<Step> steps;

    public Scenario() {
    }

    public Scenario(String name, String site, List<Step> steps) {
        this.name = name;
        this.site = site;
        this.steps = steps;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Scenario scenario = (Scenario) o;
        return
                Objects.equals(name, scenario.name) &&
                        Objects.equals(site, scenario.site) &&
                        Objects.equals(steps, scenario.steps);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, site, steps);
    }
}
