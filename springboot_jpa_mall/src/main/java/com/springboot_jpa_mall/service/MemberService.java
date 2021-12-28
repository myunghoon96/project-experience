package com.springboot_jpa_mall.service;

import com.springboot_jpa_mall.dto.MemberDto;
import com.springboot_jpa_mall.entity.Member;
import com.springboot_jpa_mall.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class MemberService implements UserDetailsService {
    @Autowired
    private MemberRepository memberRepository;

    @Transactional
    public Long addMember(MemberDto memberDto) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword()));

        Member newMember = new Member(memberDto.getMemberLoginId(), memberDto.getPassword());

        memberRepository.save(newMember);
        return newMember.getId();
    }

    @Override
    public UserDetails loadUserByUsername(String memberLoginId) throws UsernameNotFoundException {

        Optional<Member> memberWrapper = memberRepository.findByMemberLoginId(memberLoginId);
        Member member = memberWrapper.get();
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        authorities.add(new SimpleGrantedAuthority(Role.ROLE_ADMIN.name()));
        if(member == null){
            throw new UsernameNotFoundException(memberLoginId);
        }
//        return new User(member.getMemberId(), member.getPassword(), authorities);
        return User.builder()
                .username(member.getMemberLoginId())
                .password(member.getPassword())
                .roles(member.getRole().toString())
                .build();
    }

}
