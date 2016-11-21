package nl._42.autoconfig.domain;

import org.springframework.data.jpa.repository.JpaRepository;

interface DomainRepository extends JpaRepository<Domain, Long> {
}
