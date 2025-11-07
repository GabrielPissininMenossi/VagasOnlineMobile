package com.unoeste.vagasonlinemobile.entities;

public class Interesses {
    private Vaga vaga;
    private Candidato candidato;

    public Interesses(Vaga vaga, Candidato candidato) {
        this.vaga = vaga;
        this.candidato = candidato;
    }

    public Interesses() {
        this(null, null);
    }

    public Vaga getVaga() {
        return vaga;
    }

    public void setVaga(Vaga vaga) {
        this.vaga = vaga;
    }

    public Candidato getCandidato() {
        return candidato;
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }
}
