package com.wav.desafio.mappers;

import com.wav.desafio.entities.AssemblyEntity;
import com.wav.desafio.model.AssemblyDTO;
import org.mapstruct.Mapper;

@Mapper
public interface AssemblyMapper
{
    AssemblyDTO assemblyToAssemblyDTO( AssemblyEntity assemblyEntity );
}
