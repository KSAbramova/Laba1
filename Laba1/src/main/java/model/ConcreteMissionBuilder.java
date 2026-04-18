package model;

public class ConcreteMissionBuilder extends MissionBuilder {

    @Override
    public void buildMissionId(String missionId) {
        mission.setMissionId(missionId);
    }

    @Override
    public void buildDate(java.time.LocalDate date) {
        mission.setDate(date);
    }

    @Override
    public void buildLocation(String location) {
        mission.setLocation(location);
    }

    @Override
    public void buildOutcome(Mission.Outcome outcome) {
        mission.setOutcome(outcome);
    }

    @Override
    public void buildDamageCost(long damageCost) {
        mission.setDamageCost(damageCost);
    }

    @Override
    public void buildNote(String note) {
        mission.setNote(note);
    }

    @Override
    public void buildCurse(Curse curse) {
        mission.setCurse(curse);
    }

    @Override
    public void buildEconomicAssessment(EconomicAssessment economicAssessment) {
        mission.setEconomicAssessment(economicAssessment);
    }

    @Override
    public void buildCivilianImpact(CivilianImpact civilianImpact) {
        mission.setCivilianImpact(civilianImpact);
    }

    @Override
    public void buildEnemyActivity(EnemyActivity enemyActivity) {
        mission.setEnemyActivity(enemyActivity);
    }

    @Override
    public void buildEnvironmentConditions(EnvironmentConditions environmentConditions) {
        mission.setEnvironmentConditions(environmentConditions);
    }

    @Override
    public void addSorcerer(Sorcerer sorcerer) {
        mission.addSorcerer(sorcerer);
    }

    @Override
    public void addTechnique(Technique technique) {
        mission.addTechnique(technique);
    }

    @Override
    public void addOperationTimeline(OperationTimeline timeline) {
        mission.addOperationTimeline(timeline);
    }

    @Override
    public void addOperationTag(String tag) {
        mission.addOperationTag(tag);
    }

    @Override
    public void addSupportUnit(String unit) {
        mission.addSupportUnit(unit);
    }

    @Override
    public void addRecommendation(String rec) {
        mission.addRecommendation(rec);
    }

    @Override
    public void addArtifactRecovered(String artifact) {
        mission.addArtifactRecovered(artifact);
    }

    @Override
    public void addEvacuationZone(String zone) {
        mission.addEvacuationZone(zone);
    }

    @Override
    public void addStatusEffect(String effect) {
        mission.addStatusEffect(effect);
    }
}