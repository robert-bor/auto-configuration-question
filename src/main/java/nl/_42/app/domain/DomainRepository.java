package nl._42.app.domain;

import org.springframework.data.jpa.repository.JpaRepository;

interface DomainRepository extends JpaRepository<Domain, Long> {
}
