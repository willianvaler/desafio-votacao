package com.wav.desafio.services;

import com.wav.desafio.model.AssemblyDTO;

import java.util.List;

public interface AssemblyService
{
    public AssemblyDTO create( AssemblyDTO assembly );

    public AssemblyDTO getAssembly( int id );
    public List<AssemblyDTO> getAll();
}
