package com.wav.desafio.services;

import com.wav.desafio.entities.AssemblyEntity;
import com.wav.desafio.mappers.AssemblyMapper;
import com.wav.desafio.model.AssemblyDTO;
import com.wav.desafio.repositories.AssemblyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Primary
@RequiredArgsConstructor
public class AssemblyServiceImpl implements AssemblyService
{
    private final AssemblyRepository assemblyRepository;
    private final AssemblyMapper assemblyMapper;

    @Override
    public AssemblyDTO create( AssemblyDTO assembly )
    {
        return assemblyMapper.assemblyToAssemblyDTO( assemblyRepository.save( assemblyMapper.assemblyDTOToAssemblyEntity( assembly ) ) );
    }

    @Override
    public AssemblyDTO getAssembly( int id )
    {
        return Optional.ofNullable( assemblyMapper.assemblyToAssemblyDTO( assemblyRepository.findById( id ).orElse( null ) ) ).orElseThrow();
    }

    @Override
    public List<AssemblyDTO> getAll()
    {
        return assemblyRepository.findAll().stream().map( assemblyMapper::assemblyToAssemblyDTO ).collect( Collectors.toList() );
    }
}
